import requests
import json
import time
import sys,getopt
import os
import pprint
from pathlib import Path

class Feedbacks:
    def __init__(self, feedbacks):
        self.response_list = feedbacks

class Feedback:
    def __init__(self, id, action):
        self.id = id
        self.action = action
    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__,
                          sort_keys=True, indent=4)

class SddcInfo:
    def __init__(self, name, id, state):
        self.name = name
        self.id = id
        self.state = state

#https://ad3a7e5e8bcc811e895e10a307de3784-836352642.us-west-2.elb.amazonaws.com/vmc/skynet

class VmcUtil:
    def __init__(self, ip, mode, orgid, refreshtoken):
        self.ip = ip
        urlprefix, port = self.getMode(mode)
        self.urlprefix = urlprefix
        self.port = port
        if ip != 'stg' and ip != 'prd':
            self.endpoint = ip+':'+port
            self.ssbackendpoint = ip+':'+'8080'
        elif ip == 'stg':
            self.endpoint = self.getBaseUrl(mode)
            #self.endpoint = 'ad3a7e5e8bcc811e895e10a307de3784-836352642.us-west-2.elb.amazonaws.com'
        self.orgId = orgid
        self.refreshtoken = refreshtoken
        self.activeTaskUrl = 'http://{}{}/api/orgs/{}/tasks?$filter=status%20eq%20%27STARTED%27'.format(self.endpoint, urlprefix, self.orgId)
        self.anyTaskUrl = 'http://{}{}/api/orgs/{}/tasks'.format(self.endpoint,  urlprefix, self.orgId,)
        self.taskurl = 'http://{}{}/api/orgs/{}/tasks/'.format(self.endpoint, urlprefix, self.orgId)
        self.authurl = 'http://{}:8000/csp/am/api/login'.format(ip)
        self.cleanupfailedsddcurl = 'http://{}{}/api/orgs/{}/v2t/failed-cleanup'.format(self.endpoint, '/vmc',  self.orgId)
        if ip == 'stg':
            self.v2tprepareurlmigration = 'https://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/prepare?mode=migration'
            self.v2tprepareurlverification = 'https://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/prepare?mode=verification'

            self.precheckurl = 'https://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/precheck'
            self.logicalconstructurl = 'https://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/logicalconstructs'
            self.edgeurl = 'https://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/edge'
            self.infraurl = 'https://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/infrastructure'

            self.getoverallstatus = 'https://{}{}/api/orgs/{}/sddcs/{}/migration/pop/networks/api/v1/migration/status-summary'
            self.rollbackurl = 'https://{}{}/api/orgs/{}/sddcs/{}/networking/migration?action=rollback'

            self.feedbackuri = 'https://{}{}/api/orgs/{}/sddcs/{}/networking/migration/feedback'
            self.phase2prepare = 'https://{}{}/api/orgs/{}/sddcs/{}/migration?action=prepare'
            self.phase2verify = 'https://{}{}/api/orgs/{}/sddcs/{}/migration?action=verify'
            self.phase4 = 'https://{}{}/api/orgs/{}/sddcs/{}/cleanupV2T'
            self.sddcurl = 'https://{}{}/api/orgs/{}/sddcs'.format('stg.skyscraper.vmware.com', '/vmc', self.orgId)
            self.sddccreateurl = 'https://{}{}/api/orgs/{}/sddcs'.format('stg.skyscraper.vmware.com', '/vmc', self.orgId)
            self.sddcspecificurl = 'https://{}{}/api/orgs/{}/sddcs/'.format('stg.skyscraper.vmware.com', '/vmc',  self.orgId)
            self.activeTaskUrl = 'https://{}{}/api/orgs/{}/tasks?$filter=status%20eq%20%27STARTED%27'.format(self.endpoint, urlprefix, self.orgId)
            if mode != 'skynet':
                self.anyTaskUrl = 'https://{}{}/api/orgs/{}/tasks'.format('stg.skyscraper.vmware.com',  urlprefix, self.orgId)
                self.taskurl = 'https://{}{}/api/orgs/{}/tasks/'.format('stg.skyscraper.vmware.com', urlprefix, self.orgId)
            else:
                self.anyTaskUrl = 'https://{}{}/api/orgs/{}/tasks'.format(self.endpoint,  urlprefix, self.orgId)
                self.taskurl = 'https://{}{}/api/orgs/{}/tasks/'.format(self.endpoint, urlprefix, self.orgId)

        else:
            self.v2tprepareurlmigration = 'http://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/prepare?mode=migration'
            self.v2tprepareurlverification = 'http://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/prepare?mode=verification'

            self.precheckurl = 'http://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/precheck'
            self.logicalconstructurl = 'http://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/logicalconstructs'
            self.edgeurl = 'http://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/edge'
            self.infraurl = 'http://{}{}/api/orgs/{}/sddcs/{}/networking/migration/stages/infrastructure'

            self.getoverallstatus = 'http://{}{}/api/orgs/{}/sddcs/{}/migration/pop/networks/api/v1/migration/status-summary'
            self.rollbackurl = 'http://{}{}/api/orgs/{}/sddcs/{}/networking/migration?action=rollback'

            self.feedbackuri = 'http://{}{}/api/orgs/{}/sddcs/{}/networking/migration/feedback'
            self.phase2prepare = 'http://{}{}/api/orgs/{}/sddcs/{}/migration?action=prepare'
            self.phase2rollback = 'http://{}{}/api/orgs/{}/sddcs/{}/migration?action=rollback'
            self.phase2verify = 'http://{}{}/api/orgs/{}/sddcs/{}/migration?action=verify'
            self.sddcurl = 'http://{}{}/api/orgs/{}/sddcs'.format(self.ssbackendpoint, '/vmc', self.orgId)
            self.sddccreateurl = 'http://{}{}/api/orgs/{}/sddcs'.format(self.ssbackendpoint, '/vmc', self.orgId)
            self.sddcspecificurl = 'http://{}{}/api/orgs/{}/sddcs/'.format(self.ssbackendpoint, '/vmc',  self.orgId)
            self.phase4 = 'http://{}{}/api/orgs/{}/sddcs/{}/cleanupV2T'
            self.nsxtmanager = 'http://{}{}/api/orgs/{}/sddcs/{}/nsxt/manager'


        if ip != 'stg' and ip != 'prd':
            self.cspauthtoken = self.getCspToken()
            self.operatorauthtoken = self.getOperatorToken()
        elif ip == 'stg':
            self.cspauthtoken = self.getCspTokenUsingRefreshToken()
            self.operatorauthtoken = self.cspauthtoken

        #a67ba602-6689-450c-a743-8842ca6b032a {local org}

    def getMode(self, mode):
        if mode == 'skynet':
            return '/vmc/skynet', '8090'
        else:
            return '/vmc', '8080'

    def getBaseUrl(self, mode):
        if mode == 'skynet':
            return 'stg-int.skyscraper.vmware.com'
        elif mode == 'ss-backend':
            return 'stg.skyscraper.vmware.com'

    def getCspTokenUsingRefreshToken(self):
        if self.ip == 'stg':
            url = 'https://console-stg.cloud.vmware.com/csp/gateway/am/api/auth/api-tokens/authorize?refresh_token={}'.format(self.refreshtoken)
        r = requests.post(url)
        data = r.json()
        return data['access_token']


    def getCspToken(self):
        if self.ip == 'stg':
            return self.getCspTokenUsingRefreshToken()
        data = {'username':'csp@vmware.com','password':'VMware@123'}
        headers = {'Content-type': 'application/json'}
        url = self.authurl.format(self.ip)
        r = requests.post(url, data = json.dumps(data), headers=headers)
        data = r.json()
        return data['cspAuthToken']

    def getOperatorToken(self):
        if (self.ip == 'stg'):
            return self.getCspTokenUsingRefreshToken()
        #TODO remove all operator endpoints
        data = {'username':'operator@vmware.com','password':'VMware@123'}
        headers = {'Content-type': 'application/json'}
        url = self.authurl.format(self.ip)
        r = requests.post(url, data = json.dumps(data), headers=headers)
        data = r.json()
        return data['cspAuthToken']

    def createSddc(self):
        name = input("Enter SDDC name$")
        popami = input("Enter POP AMI$")
        numberofhosts = input("Enter Number of hosts, default[2]$")
        esxnsxvami = input("Enter ESX NSX-V AMI$")
        esxnsxtami = input("Enter ESX NSX-T AMI$")
        glcmbundle = input("Enter GLCM bundle$")
        inputvpccidr = input("Enter VPC CIDR [10.2.0.0/16]$")
        if len(inputvpccidr) > 0:
            vpccidr = inputvpccidr
        else:
            vpccidr = '10.2.0.0/16'
        networktype = input("Enter network type[AWS_MULTI_AZ_NSX_V], Enter T for AWS_MULTI_AZ_NSX_T$")
        if networktype == 'T':
            nettype = 'AWS_MULTI_AZ_NSX_T'
        else:
            nettype = 'AWS_MULTI_AZ_NSX_V'

        payload = self.buildpayload(name, popami, esxnsxvami, esxnsxtami, glcmbundle, numberofhosts, vpccidr, nettype)

        print('Payload to create sddc\n' + payload)

        proceed = input('press y to proceed$')
        if proceed != 'y' and proceed != 'Y':
            return
        headers = {'Content-Type':'application/json'}
        r = self.executeAsCsp(self.sddccreateurl, headers, False, payload)
        if r.status_code == 202:
            taskId = r.json()["id"]
            taskstatus = self.taskStatus(taskId, True)

            if taskstatus:
                print('sddc creation succeeded')
                return True
            else:
                print('sddc creation failed')
                return False
        else:
            print(r.text)

    def buildpayload(self, sddcname, popami, esxnsxvami, esxnsxtami, glcmbundle, numberofhosts='2', vpccidr= '10.2.0.0/16', networktype='AWS_MULTI_AZ_NSX_V'):
        payload = '{'
        payload += '    "name": "'+sddcname+'",\n'
        payload += '    "provider": "AWS",\n'
        payload += '    "region": "US_WEST_2",\n'
        if popami is not None and len(popami) > 0:
            payload += '    "pop_ami": "'+popami+'",\n'
        payload += '    "num_hosts": "'+numberofhosts+'",\n'
        payload += '    "vpc_cidr": "'+vpccidr+'",\n'
        if esxnsxvami is not None and len(esxnsxvami) > 0:
            payload += '    "esx_ami": "'+esxnsxvami+'",\n'
        if popami is not None and len(popami) > 0:
            payload += '    "sddc_manifest": {\n'
            payload += '        "vmc_version": "1.5 Patch 01",\n'
            payload += '        "vmc_internal_version": "1.5.1.26",\n'
            payload += '        "pop_info": {\n'
            payload += '        "ami_infos": {\n'
            payload += '        "us-west-2": {\n'
            payload += '        "region": "us-west-2",\n'
            payload += '        "type": "POP",\n'
            payload += '        "id": "'+popami+'"\n'
            payload += '     }\n'
            payload += '   }\n'
            payload += ' },\n'
        payload += ' "schema_version": "1.1",\n'
        if esxnsxvami is not None and len(esxnsxvami) > 0:
            payload += ' "esx_ami": {\n'
            payload += '    "region": "US_WEST_2",\n'
            payload += '    "id": "'+esxnsxvami+'",\n'
            payload += '    "name": "rst-pvt-cloud-release-esx-ami"\n'
            payload += ' },\n'
        if esxnsxtami is not None and len(esxnsxtami) > 0:
            payload += ' "esx_nsxt_ami": {\n'
            payload += '    "region": "US_WEST_2",\n'
            payload += '    "id": "'+esxnsxtami+'",\n'
            payload += '    "name": "rst-pvt-cloud-release-esxt-ami"\n'
            payload += ' },\n'
        if glcmbundle is not None and len(glcmbundle) > 0:
            payload += ' "glcm_bundle": {\n'
            payload += '     "id": "'+glcmbundle+'",\n'
            payload += '     "s3_bucket": "vmw-sddc-ci"\n'
            payload += ' }\n'
        payload += '},\n'
        payload += '"network_type": "'+networktype+'",\n'
        if self.ip == 'stg':
            payload += '"account_link_sddc_config":[{"customer_subnet_ids":["subnet-ca9300b3"],"connected_account_id":"8faa134f-8ce0-391e-affc-6eb7d8fce6a1"}],\n'
        payload += '"vxlan_subnet": "192.168.1.0/24"\n'
        payload += '}\n'

        return payload

    def createSddcFromFile(self):
        home = str(Path.home())
        path = home+'/sddc.txt'
        file = open(path,'r')
        content = file.read()
        print('Payload to create sddc\n' + content)

        if self.yes() is False:
            return

        headers = {'Content-Type':'application/json'}
        r = self.executeAsCsp(self.sddcurl, headers, False, content)
        if r.status_code == 200:
            taskId = r.json()["id"]
            taskstatus = self.taskStatus(taskId)

            if taskstatus:
                print('sddc creation succeeded')
                return True
            else:
                print('sddc creation failed')
                return False
        else:
            print(r.text)

    def getSddcsStatus(self):
        id = self.getSddcId()
        url = self.sddcspecificurl + id
        response = self.executeAsCsp(url, {}, True, None)
        data = response.json()
        print('VC IP '+':'+data['resource_config']['vc_ip'])
        print('VC admin user name '+':'+data['resource_config']['admin_username'])
        print('VC admin password '+':'+data['resource_config']['admin_password'])
        print('NSX-V user name '+':'+data['resource_config']['nsx_manager_username'])
        print('NSX-V password '+':'+data['resource_config']['nsx_manager_password'])
        if data['resource_config']['sddc_manifest']['esx_ami']['id'] is not None:
            print('ESX NSX-V AMI '+':'+data['resource_config']['sddc_manifest']['esx_ami']['id'])
        if data['resource_config']['sddc_manifest']['esx_ami']['id'] is not None:
            print('ESX NSX-T AMI '+':'+data['resource_config']['sddc_manifest']['esx_nsxt_ami']['id'])
        if data['resource_config']['sddc_manifest']['pop_info']['ami_infos']['us-west-2']['id'] is not None:
            print('POP AMI '+':'+data['resource_config']['sddc_manifest']['pop_info']['ami_infos']['us-west-2']['id'])

        if 'nsxt_manager_username'in data['resource_config']:
            print('NSX-T user name '+':'+data['resource_config']['nsxt_manager_username'])
        if 'nsxt_manager_password' in data['resource_config'] :
            print('NSX-T password '+':'+data['resource_config']['nsxt_manager_password'])


    def getSddcId(self):
        url = self.sddcurl
        response = self.executeAsCsp(url, headers={}, isGet=True, payload=None)
        data = response.json()
        sddcids=[]
        sddcs = list(map(lambda sddc : SddcInfo(sddc['name'], sddc['id'], sddc['sddc_state']),
                         filter(lambda sddc : sddc['provider'] == 'AWS', data)))

        sddcs.sort(key=lambda x: x.name)
        index = 0

        for sddc in sddcs:
            print(str(index)+':'+sddc.name+': ['+sddc.state+']'+' : ['+sddc.id+']')
            index = index + 1
            sddcids.append(sddc.id)
        index = int(input("Enter SDDC index >"))
        id = sddcids[index]
        return id

    def getTaskStatus(self):
        response = self.executeAsCsp(self.activeTaskUrl, headers={}, isGet= True, payload=None )
        data = response.json()
        taskids = []
        index = 0
        for task in data:
            print(str(index)+': ['+task['id']+' : ['+task['task_type']+'] : ['+task['sub_status']+'] : ['+str(task['progress_percent'])+' ]')
            index = index + 1
            taskids.append(task['id'])
        index = int(input("Enter Task index? "))
        self.taskStatus(taskids[index])

    def taskStatus(self, taskid, islongrunning= False):
        url = self.taskurl + taskid
        headers = {'Content-type': 'application/json'}
        r = self.executeAsCsp(url, headers, True, None)
        while True:
            data = r.json()
            if data['status'] == 'FAILED':
                print('Task {} is completed with status {}'.format(taskid, data['status']))
                return False
            elif data['status'] == 'FINISHED':
                print('Task {} is completed with status {}'.format(taskid, data['status']))
                return True
            else:
                #print('Current status is {}, task {}, sub status {}, progress {}'.format(data['status'], taskid, data['sub_status'] , str(data['progress_percent'])))
                print('Current status is {}, task {}'.format(data['status'], taskid))
                if 'sub_status' in data:
                    print(data['sub_status'])
            if islongrunning:
                time.sleep(30)
            else:
                time.sleep(30)
            r = self.executeAsCsp(url, headers, True, None)

    """
    def executeAsOperator(self, url, headers, isGet, payload):
        print('Executing URL ' + url)
        headers['csp-auth-token'] = self.operatorauthtoken
        if isGet:
            r = requests.get(url, headers=headers)
        else:
            # only post is supported
            r = requests.post(url, data=payload, headers=headers)

        if r.status_code == 403:
            print('auth token expired for url ' + url + 'refreshing the auth token')
            operatorautoken = self.getOperatorToken()
            headers['csp-auth-token'] = operatorautoken
            if isGet:
                r = requests.get(url, headers=headers)
            else:
                # only post is supported
                r = requests.post(url, data=payload, headers=headers)

            if r.status_code != 403:
                self.operatorauthtoken = operatorautoken
                return r
            else:
                raise Exception('Unable to refresh operator auth token for url {}', url)
        return r
    """
    def taskDetailedStatus(self):
        taskid = input('Enter task id');
        url = self.taskurl + taskid
        headers = {'Content-type': 'application/json'}
        r = self.executeAsCsp(url, headers, True, None)
        print(r.text)

    def executeAsCsp(self, url, headers, isGet, payload):
        headers['csp-auth-token'] = self.cspauthtoken
        if isGet:
            r = requests.get(url, headers=headers)
        else:
            # only post is supported
            r = requests.post(url, data=payload, headers=headers)

        if r.status_code == 403 or r.status_code == 401:
            print('auth token expired for url '+ url + 'refreshing the auth token')
            csptoken = self.getCspToken()
            headers['csp-auth-token'] = csptoken
            if isGet:
                r = requests.get(url, headers=headers)
            else:
                # only post is supported
                r = requests.post(url, data=payload, headers=headers)

            if r.status_code != 403:
                self.cspauthtoken = csptoken
                return r
            else:
                raise Exception('Unable to refresh operator auth token for url {}', url)
        return r

    def executePrecheckStartSkipMcUpdate(self, sddcid):
        url = self.precheckurl.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=start&skipMcUpdate=true'
        self.executePostTaskAndWait(url)

    def executePrepareMigration(self, skipMcUpdate, sddcid):
        if skipMcUpdate == False:
            url = self.v2tprepareurlmigration.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        else:
            url = self.v2tprepareurlmigration.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'&skipMcUpdate=true'
        self.executePostTaskAndWait(url)

    def executePostTaskAndWait(self, url):
        print('Executing '+url + ' method POST')
        r = self.executeAsCsp(url, headers={}, isGet=False, payload=None)
        if 'id' in r.json():
            taskId = r.json()['id']

            taskstatus = self.taskStatus(taskId)

            if taskstatus:
                print('Task succeeded')
                return True
            else:
                print('Task failed')
                return False
        else:
            print("Unexpected response received")
            print(r.text)
            return False

    def executePrecheckStart(self, sddcid):
        url = self.precheckurl.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=start'
        self.executePostTaskAndWait(url)

    def executePrecheckRerun(self, sddcid):
        url = self.precheckurl.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=rerun'
        self.executePostTaskAndWait(url)

    def executeFailedSddcCleanup(self):
        r = self.executeAsCsp(self.cleanupfailedsddcurl, {}, True, None)
        print(r.status_code)

    def executePrecheckContinue(self, sddcid):
        url = self.precheckurl.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=continue'
        self.executePostTaskAndWait(url)


    def executeRollback(self, sddcid):
        url = self.rollbackurl.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        self.executePostTaskAndWait(url)

    def getFeedback(self, sddcid):
        uri = self.feedbackuri.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        headers = {'Content-type': 'application/json'}
        r = self.executeAsCsp(uri, headers, True, None)
        print(r.text)
        return r.text

    def getOverallstatus(self, sddcid):
        if sddcid is None or len(sddcid) == 0:
            sddcid = self.getSddcId()
        uri = self.getoverallstatus.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        r = self.executeAsCsp(uri, headers={'Content-type': 'application/json'}, isGet=True, payload=None)
        print(r.text)
        return r.text

    def executePhaseTwoPrepare(self, sddcid):
        uri = self.phase2prepare.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        headers = {'Content-type': 'application/json'}
        r = self.executeAsCsp(uri, headers=headers, isGet=False, payload= None)
        taskId = r.json()['id']
        self.taskStatus(taskId, True)

    def executePhaseTwoRollback(self, sddcid):
        uri = self.phase2rollback.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        headers = {'Content-type': 'application/json'}
        r = self.executeAsCsp(uri, headers=headers, isGet=False, payload= None)
        taskId = r.json()['id']
        self.taskStatus(taskId)

    def executePhase2Verify(self, sddcid):
        uri = self.phase2verify.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        headers = {'Content-type': 'application/json'}
        r = self.executeAsCsp(uri, headers = headers, isGet = False, payload = None)
        taskId = r.json()['id']
        self.taskStatus(taskId)

    def applyfeedback(self, sddcid, prompt):
        headers = {'Content-type': 'application/json'}
        url = self.feedbackuri.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        feedbacks = self.checkfeedback(headers, url)
        if len(feedbacks) > 0:
            response = self.preparefeedbackresponse(feedbacks)
            print('feedback \n')
            print(response)
            if prompt and self.yes():
                task = self.executefeedbackResponse(url, headers, response)
                taskId = task.json()['id']
                self.taskStatus(taskId)
            else:
                task = self.executefeedbackResponse(url, headers, response)
                taskId = task.json()['id']
                self.taskStatus(taskId)


    def executeLogicalConstruct(self, sddcid):
        url = self.logicalconstructurl.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=continue'
        self.executePostTaskAndWait(url)

    def executeEdge(self, sddcid):
        url = self.edgeurl.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=continue'
        self.executePostTaskAndWait(url)

    def executeInfra(self, sddcid):
        url = self.infraurl.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=continue'
        self.executePostTaskAndWait(url)

    def yes(self):
        val = input('press y to continue');
        if val == 'y' or val == 'Y':
            return True
        return False

    def startMigration(self):
        isprepareneeded = input("Execute migration prepare ? (y/n)")
        cspauthtoken = self.getOperatorToken()
        headers = {'Content-type': 'application/json', 'csp-auth-token':cspauthtoken}
        sddcid = self.getSddcId()

        if isprepareneeded == 'y':
            print("preparation needed")
            isverification = input("Is this in verification mode? (y/n)")
            if isverification == 'y':
                url = self.v2tprepareurlverification
            else:
                url = self.v2tprepareurlmigration
            url = url.format(sddcid)
            r = requests.post(url, headers=headers)
            taskId = r.json()["id"]
            taskstatus = self.taskStatus(taskId)

            if taskstatus:
                print('preparation succeeded')
            else:
                print("preparation failed")
                return
        else:
            print("preparation not needed")

        url = self.getcomponentstatus.format(sddcid)
        cspauthtoken = self.getOperatorToken()
        headers = {'Content-type': 'application/json', 'csp-auth-token':cspauthtoken}
        r = requests.get(url, headers=headers)

        action_dict = {1:'start', 2:'continue', 3:'rollback', 4: 'feedback', 5: 'next', 6: 'exit'}
        stage_dict = {'PRECHECK': 'precheck', 'LOGICAL_CONSTRUCTS': 'logicalconstructs', 'EDGE': 'edge', 'INFRASTRUCTURE':'insfrastructure'}

        print("current status")
        pprint.pprint(r.json())
        statuses = ['PRECHECK', 'LOGICAL_CONSTRUCTS', 'EDGE', 'INFRASTRUCTURE']
        i = 0
        while i < len(statuses):
            status = statuses[i]
            print('Current stage '+status)
            currentstatus = self.check_status(status, sddcid);
            if currentstatus == "SUCCESS":
                print(status+" has completed successfully")
            elif currentstatus == "IN_PROGRESS":
                print('currenst status' + currentstatus + 'Waiting' )
                time.sleep(10)
                continue
            else:
                action = self.getActionFromUser(action_dict)
                if action_dict.get(action) == 'exit':
                    return
                elif action_dict.get(action) == 'start' or action_dict.get(action) == 'continue':
                    self.execute(sddcid, headers, stage_dict.get(status), action_dict.get(action))
                elif action_dict.get(action) == 'rollback':
                    self.applyrollback( sddcid, headers)
                elif action_dict.get(action) == 'next':
                    i = i + 1
                elif action_dict.get(action) == 'feedback':
                    feedbacks = self.checkfeedback(headers, None)
                    if len(feedbacks) > 0:
                        response = self.preparefeedbackresponse(feedbacks)
                        #feedbacklist = Feedbacks(response)
                        #data = {}
                        #data['response_list'] = response
                        task = self.applyfeedback(sddcid, headers, json.loads(response))
                        taskId = task.json()["id"]
                        self.taskStatus(taskId)
            url = self.getcomponentstatus.format(sddcid)
            r = requests.get(url, headers=headers)
            pprint.pprint(r.json())



        """
        components = (r.json()["component_status"])
        overall_status = (r.json()["overall_migration_status"])
        prechecksuccessful = self.precheck(overall_status, serverip, sddcid, headers)

        if prechecksuccessful is False:
            feedbackpresent = self.checkfeedback(serverip, sddcid, headers)
            if len(feedbackpresent) > 0:
                feedbackresponse = self.preparefeedbackresponse(feedbackpresent)
                response = self.applyfeedback(feedbackresponse)
                if response.status_code == 200 or response.status_code == 201:
                    prechecksuccessful = self.precheck(overall_status, serverip, sddcid, headers)
            else:
                prechecksuccessful = self.precheck(overall_status, serverip, sddcid, headers)

        if prechecksuccessful is False:
            print("Precheck failed. Aborting migration")
        #if logicalsuccess:
        """
    def getActionFromUser(self, dict):
        print(dict)
        value = input("Enter action : ")
        return int(value)

    def executefeedbackResponse(self, url, headers, data):
        #r = requests.post(feedbackuri, json=data, headers=headers)
        r = self.executeAsCsp(url, headers, False, data)
        return r

    def applyrollback(self, url, headers):
        r = requests.post(url, headers=headers)
        return r

    def checkfeedback(self, headers, url):
        r = self.executeAsCsp(url, headers = headers, isGet=True, payload=None)
        retVal = []
        if r.status_code == 200 and 'results' in r.json():
            results = r.json()["results"]
            for r in results:
                if r.get('vertical') == 'EDGE' and r.get('sub_vertical') == 'FIREWALL':
                    retVal.append(r.get('id'))
                else:
                    return None
        return retVal

#{"response_list":[{"id":"143-9139137058749679128-edge-1:131074","action":"skip"},
#                 > {  "id":"143-9139137058749679128-edge-1:131075","action":"skip"}]}
    def preparefeedbackresponse(self, ruleids):
        response = []
        response.append('{"response_list":[')
        index = 0
        for id in ruleids:
            response.append('{"id":"'+id+'","action":"skip"}')
            if index < len(ruleids)-1:
                response.append(',')
            index = index + 1
        response.append(']}')
        retval = ''.join(response)
        return retval
        #feedbacks = []
        #for id in ruleids:
        #    feedback = Feedback(id, 'skip')
        #    feedbacks.append(feedback)
        #return feedbacks

    def execute(self, serverip, sddcid, headers, stage, action):
        logcaluri = 'http://{}:8090/vmc/skynet/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/{}?action={}'.format(serverip, sddcid,stage, action)
        r = requests.post(logcaluri, headers=headers)
        taskId = r.json()["id"]

        taskstatus = self.taskStatus(taskId)

        if taskstatus:
            print(stage + 'succeeded')
            return True
        else:
            print(stage+ 'failed')
            return False

    def precheck(self, overall_status, serverip, sddcid, headers):
        if overall_status == 'NOT_STARTED':
            precheckurl = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/precheck?action=start'.format(serverip, sddcid)
        else:
            precheckurl = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/precheck?action=continue'.format(serverip, sddcid)

        if precheckurl is not None:
            requests.post(precheckurl, headers=headers)
            return self.wait_till_success_or_failure(sddcid, 'PRECHECK')

    def wait_till_success_or_failure(self, sddcid, stage):
        while True:
            componentstatus = self.check_status(stage, sddcid)
            if componentstatus == "SUCCESS":
                print(stage + ' succeeded')
                return True
            elif componentstatus == "FAILED":
                print(stage + ' failed')
                return False
            elif componentstatus == "NOT_STARTED":
                print(stage + ' failed')
                return False
            else:
                print(stage + ' is still running')
                continue

    def check_status(self, stagename, sddcid):
        url = self.getoverallstatus.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        r = self.executeAsCsp(url, {}, True, None)
        #cspauthtoken = self.getOperatorToken()
        #headers = {'Content-type': 'application/json', 'csp-auth-token':cspauthtoken}
        #r = requests.get(url, headers=headers)

        components = (r.json()["component_status"])
        for component in components:
            if component["component_type"] == stagename:
                return component["status"]

    def executePhase4(self, sddcid):
        url = self.phase4.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=all'
        self.executePostTaskAndWait(url)
        #url = self.phase4.format(self.endpoint, self.urlprefix, self.orgId, sddcid)+'?action=deleteEsx'
        #self.executePostTaskAndWait(url)

    def executeE2Ev2t(self, sddcid):
        #self.executePrepareVerification(False, sddcid)
        self.executePrecheckStart(sddcid)
        self.applyfeedback(sddcid, False)
        self.executePrecheckContinue(sddcid)
        status = self.check_status('PRECHECK', sddcid)
        if status == 'FAILED' or status != 'NOT_STARTED':
            print('Aborting migration process as PRECHECK FAILED')
            return
        self.executePrecheckRerun(sddcid)
        self.executePhaseTwoPrepare(sddcid)
        self.executePrepareMigration(False, sddcid)
        self.executePrecheckRerun(sddcid)
        status = self.check_status('PRECHECK', sddcid)
        if status == 'FAILED' or status != 'NOT_STARTED':
            self.applyfeedback(sddcid, False)
            self.executePrecheckContinue(sddcid)
            status = self.check_status('PRECHECK', sddcid)
            if status == 'FAILED':
                print(self.getOverallstatus(sddcid))
                print('Aborting migration process as PRECHECK FAILED')
                return
        self.executeLogicalConstruct(sddcid)
        self.executeEdge(sddcid)
        self.executeInfra(sddcid)
        self.executePhase4(sddcid)

    def getActionFromUser(self, dict):
        print(dict)
        value = input("Enter action : ")
        return int(value)

    def getNsxtManagerDetails(self, sddcid):
        uri = self.nsxtmanager.format(self.endpoint, self.urlprefix, self.orgId, sddcid)
        headers = {'Content-type': 'application/json'}
        r = self.executeAsCsp(uri, headers, True, None)
        print(r.text)
        return r.text


def showusage():
    print("==================================")
    print("press 1 to get SDDC info")
    print("press 2 to get active task status")
    print("press 3 to create SDDC")
    print("press 4 to execute /networking/stages/prepare?mode=verification(phase3)")
    print("press 5 to execute /networking/stages/prepare?mode=migration(phase3)")
    print("press 6 to execute /networking/stages/precheck?action=start(phase3)")
    print("press 7 to execute /networking/stages/precheck?action=continue(phase3)")
    print("press 8 to execute (GET)/networking/stages/feedback(phase3)")
    print("press 9 to execute (POST)/networking/stages/feedback(phase3)")
    print("press 10 to execute /networking/stages/logicalconstructs?action=continue(phase3)")
    print("press 11 to execute /networking/stages/edge?action=continue(phase3)")
    print("press 12 to execute /networking/stages/infrastructure?action=continue(phase3)")
    print("press 13 to execute migration?action=prepare(phase2)")
    print("press 14 to execute migration?action=verify(phase2)")
    print("press 15 to execute cleanupV2T?action=all")
    print("press 16 to get overall status summary (phase3)")
    print("press 17 to execute /networking/stages/precheck?action=start&skipMcUpdate=true(phase3)")
    print("press 18 to execute /networking/stages/prepare?mode=migration&skipMcUpdate=true(phase3)")
    print("press 19 to check task status")
    print("press 20 to rollback (Phase3)")
    print("press 21 to cleanup failed sddcs (Phase3)")
    print("press 22 to create sddc (content from file)")
    print("press 23 to execute E2E V2T")
    print("press 24 to execute /networking/stages/precheck?action=rerun(phase3)")
    print("press 25 to execute migration?action=rollback(phase2)")
    print("press 26 to get NSX-T manager details")
    print("press 27 to get task details")
    print("press e to exit")
    print("==================================")



def main(argv):
    server = os.environ['SERVER_HOST']
    orgid = os.environ.get('V2T_ORG_ID')
    refreshtoken = os.environ['REFRESH_TOKEN']
    vmcmode = os.environ['VMC_MODE']

    #for stage
    #orgId = 'ba6a3e3e-2cb5-45eb-bc73-b046c2545dcd'
    #os.environ['V2T_ORG_ID']
    #stgrefreshtoken = 'b2afd3d4-26eb-4e2f-8bdf-a4a5e9fe08a7'
    #os.environ['V2T_REFRESH_TOKEN']

    #localorgId = 'a67ba602-6689-450c-a743-8842ca6b032a'
    #localrefreshtoken = ""



    #mode = input("Enter 1 for skynet, 2 for ss-backend >")
    #if mode == '1':
    #    vmcmode = 'skynet'
    #else:
    #    vmcmode = 'ss-backend'
    print("Env [set \'stg\' for stage env ] >>" + server)
    print("VMC Mode >>" + vmcmode)
    print('refresh token >>' + refreshtoken)
    print('V2T org id >>' + orgid)

    ifyes = input('Type y to proceed>>')
    if ifyes != 'y':
        return

    util = VmcUtil(server, vmcmode, orgid, refreshtoken)
    while True:
        showusage()
        choice = input('Enter your choice >')
        if choice == '1':
            util.getSddcsStatus()
        elif choice == '2':
            util.getTaskStatus()
        elif choice == '3':
            util.createSddc()
        elif choice == '4':
            sddcid = util.getSddcId()
            util.executePrecheckStartSkipMcUpdate(sddcid)
        elif choice == '5':
            sddcid = util.getSddcId()
            util.executePrepareMigration(False, sddcid)
        elif choice == '6':
            sddcid = util.getSddcId()
            util.executePrecheckStart(sddcid)
        elif choice == '7':
            sddcid = util.getSddcId()
            util.executePrecheckContinue(sddcid)
        elif choice == '8':
            sddcid = util.getSddcId()
            util.getFeedback(sddcid)
        elif choice == '9':
            sddcid = util.getSddcId()
            util.applyfeedback(sddcid, True)
        elif choice == '10':
            sddcid = util.getSddcId()
            util.executeLogicalConstruct(sddcid)
        elif choice == '11':
            sddcid = util.getSddcId()
            util.executeEdge(sddcid)
        elif choice == '12':
            sddcid = util.getSddcId()
            util.executeInfra(sddcid)
        elif choice == '13':
            sddcid = util.getSddcId()
            util.executePhaseTwoPrepare(sddcid)
        elif choice == '14':
            sddcid = util.getSddcId()
            util.executePhase2Verify(sddcid)
        elif choice == '14':
            sddcid = util.getSddcId()
            util.executePhase4(sddcid)
        elif choice == '16':
            sddcid = util.getSddcId()
            util.getOverallstatus(sddcid)
        elif choice == '17':
            sddcid = util.getSddcId()
            util.executePrecheckStartSkipMcUpdate(sddcid)
        elif choice == '18':
            sddcid = util.getSddcId()
            util.executePrepareMigration(True, sddcid)
        elif choice == '19':
            taskId = input('Get Task id >')
            util.taskStatus(taskId)
        elif choice == '20':
            sddcid = util.getSddcId()
            util.executeRollback(sddcid)
        elif choice == '21':
            util.executeFailedSddcCleanup()
        elif choice == '22':
            util.createSddcFromFile()
        elif choice == '23':
            sddcid = util.getSddcId()
            util.executeE2Ev2t(sddcid)
        elif choice == '24':
            sddcid = util.getSddcId()
            util.executePrecheckRerun(sddcid)
        elif choice == '25':
            sddcid = util.getSddcId()
            util.executePhaseTwoRollback(sddcid)
        elif choice == '26':
            sddcid = util.getSddcId()
            util.getNsxtManagerDetails(sddcid)
        elif choice == '27':
            util.taskDetailedStatus()
        elif choice == 'e':
            break
        else:
            continue


if __name__ == '__main__':
    main(sys.argv)

#util = VmcUtil('10.33.79.159','ss-backend')
#util.getTaskStatus()
#util.getSddcsStatus()
#util.createSddc()
#util.startMigration()
#util.getTaskStatus('f2b1330f-2745-4a5c-8cf0-5beddbd78d46')
#util.startMigration()


