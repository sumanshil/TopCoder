import requests
import json
import time
import sys,getopt
import os
import pprint

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

class VmcUtil:
    def __init__(self, ip):
        self.ip = ip
        self.activeTaskUrl = 'http://{}:8080/vmc/api/operator/tasks?$filter=status%20eq%20%27STARTED%27'
        self.taskurl = 'http://{}:8090/vmc/skynet/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/tasks/{}'
        self.authurl = 'http://{}:8000/csp/am/api/login'
        self.sddcurl = 'http://{}:8080/vmc/api/operator/sddcs/'
        self.sddcspecificurl = 'http://{}:8080/vmc/api/operator/sddcs/{}'
        self.v2tprepareurlmigration = 'http://{}:8090/vmc/skynet/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/prepare?mode=migration'
        self.v2tprepareurlverification = 'http://{}:8090/vmc/skynet/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/prepare?mode=verification'
        self.getprecheckstatus = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/precheck'
        self.getcomponentstatus = 'http://{}:8090/vmc/skynet/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/migration/pop/networks/api/v1/migration/status-summary'
        self.rollbackurl = 'http://{}:8090/vmc/skynet/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration?action=rollback'


    def getCspToken(self):
        data = {'username':'csp@vmware.com','password':'VMware@123'}
        headers = {'Content-type': 'application/json'}
        url = self.authurl.format(self.ip)
        r = requests.post(url, data = json.dumps(data), headers=headers)
        data = r.json()
        return data['cspAuthToken']

    def getOperatorToken(self):
        data = {'username':'operator@vmware.com','password':'VMware@123'}
        headers = {'Content-type': 'application/json'}
        url = self.authurl.format(self.ip)
        r = requests.post(url, data = json.dumps(data), headers=headers)
        data = r.json()
        return data['cspAuthToken']

    def getSddcsStatus(self):
        cspauthtoken = self.getOperatorToken()
        url = self.sddcurl.format(self.ip)
        # url = 'http://'+self.ip+':8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/tasks/'+taskid
        headers = {'csp-auth-token':cspauthtoken}
        response = requests.get(url, headers=headers)
        data = response.json()
        sddcids=[]
        index = 0
        for sddc in data:
            if sddc['provider'] == "AWS":
                print(str(index)+':'+sddc['name']+': ['+sddc['sddc_state']+']')
                index = index + 1
                sddcids.append(sddc['id'])
        index = int(input("Enter SDDC index? "))
        if index <= len(sddcids):
            id = sddcids[index]
            url = self.sddcspecificurl.format(self.ip, id)
            response = requests.get(url, headers=headers)
            data = response.json()
            print('VC IP '+':'+data['resource_config']['vc_ip'])
            print('VC admin user name '+':'+data['resource_config']['admin_username'])
            print('VC admin password '+':'+data['resource_config']['admin_password'])
            print('NSX-V user name '+':'+data['resource_config']['nsx_manager_username'])
            print('NSX-V password '+':'+data['resource_config']['nsx_manager_password'])
            if data['resource_config']['nsxt_manager_username'] is not None:
                print('NSX-T user name '+':'+data['resource_config']['nsxt_manager_username'])
            if data['resource_config']['nsxt_manager_password'] is not None:
                print('NSX-T password '+':'+data['resource_config']['nsxt_manager_password'])
            if data['resource_config']['sddc_manifest']['esx_ami']['id'] is not None:
                print('ESX NSX-V AMI '+':'+data['resource_config']['sddc_manifest']['esx_ami']['id'])
            if data['resource_config']['sddc_manifest']['esx_ami']['id'] is not None:
                print('ESX NSX-T AMI '+':'+data['resource_config']['sddc_manifest']['esx_nsxt_ami']['id'])
            if data['resource_config']['sddc_manifest']['pop_info']['ami_infos']['us-west-2']['id'] is not None:
                print('ESX NSX-T AMI '+':'+data['resource_config']['sddc_manifest']['pop_info']['ami_infos']['us-west-2']['id'])



    #TODO refactor the above code to get sddc id from the following method
    def getSddcId(self):
        cspauthtoken = self.getOperatorToken()
        url = self.sddcurl.format(self.ip)
        # url = 'http://'+self.ip+':8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/tasks/'+taskid
        headers = {'csp-auth-token':cspauthtoken}
        response = requests.get(url, headers=headers)
        data = response.json()
        sddcids=[]
        index = 0
        for sddc in data:
            print(str(index)+':'+sddc['name'])
            index = index + 1
            sddcids.append(sddc['id'])
        index = int(input("Enter SDDC index? "))
        if index <= len(sddcids):
            id = sddcids[index]
            return id

    def getTaskStatus(self):
        cspauthtoken = self.getOperatorToken()
        url = self.activeTaskUrl.format(self.ip)
        headers = {'csp-auth-token':cspauthtoken}
        response = requests.get(url, headers=headers)
        data = response.json()
        taskids = []
        index = 0
        for task in data:
            print(str(index)+': ['+task['id']+' : ['+task['task_type']+'] : ['+task['sub_status']+'] : ['+str(task['progress_percent'])+' ]')
            index = index + 1
            taskids.append(task['id'])
        index = int(input("Enter Task index? "))
        self.taskStatus(taskids[index])

    def taskStatus(self, taskid):
        cspauthtoken = self.getOperatorToken()
        url = self.taskurl.format(self.ip, taskid)
        headers = {'Content-type': 'application/json', 'csp-auth-token':cspauthtoken}
        r = requests.get(url, headers=headers)
        while r.status_code == 200:
            data = r.json()
            if data['status'] == 'FAILED':
                print('Task {} is completed with status {}'.format(taskid, data['status']))
                return False
            elif data['status'] == 'FINISHED':
                print('Task {} is completed with status {}'.format(taskid, data['status']))
                return True
            else:
                print('Current status is {}, task {}, progress {}'.format(data['status'], taskid, str(data['progress_percent'])))
            time.sleep(5)
            r = requests.get(url, headers=headers)


    def startMigration(self):
        isprepareneeded = input("Execute migration prepare ? (y/n)")
        serverip = self.ip
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
            url = url.format(serverip, sddcid)
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

        url = self.getcomponentstatus.format(serverip, sddcid)
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
            currentstatus = self.check_status(status, serverip, sddcid);
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
                    self.execute(serverip, sddcid, headers, stage_dict.get(status), action_dict.get(action))
                elif action_dict.get(action) == 'rollback':
                    self.applyrollback(serverip, sddcid, headers)
                elif action_dict.get(action) == 'next':
                    i = i + 1
                elif action_dict.get(action) == 'feedback':
                    feedbacks = self.checkfeedback(serverip, sddcid, headers)
                    if len(feedbacks) > 0:
                        response = self.preparefeedbackresponse(feedbacks)
                        #feedbacklist = Feedbacks(response)
                        #data = {}
                        #data['response_list'] = response
                        task = self.applyfeedback(serverip, sddcid, headers, json.loads(response))
                        taskId = task.json()["id"]
                        self.taskStatus(taskId)
            url = self.getcomponentstatus.format(serverip, sddcid)
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

    def applyfeedback(self, serverip, sddcid, headers, data):
        feedbackuri = 'http://{}:8090/vmc/skynet/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/feedback'.format(serverip, sddcid)
        r = requests.post(feedbackuri, json=data, headers=headers)
        return r

    def applyrollback(self, serverip, sddcid, headers):
        rollbackuri = self.rollbackurl.format(serverip, sddcid)
        r = requests.post(rollbackuri, headers=headers)
        return r

    def checkfeedback(self, serverip, sddcid, headers):
        feedbackuri = 'http://{}:8090/vmc/skynet/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/feedback'.format(serverip, sddcid)
        r = requests.get(feedbackuri, headers=headers)
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
            return self.wait_till_success_or_failure(sddcid, serverip, 'PRECHECK')

    def wait_till_success_or_failure(self, sddcid, serverip, stage):
        while True:
            componentstatus = self.check_status(stage, serverip, sddcid)
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

    def check_status(self, stagename, serverip, sddcid):
        url = self.getcomponentstatus.format(serverip, sddcid)
        cspauthtoken = self.getOperatorToken()
        headers = {'Content-type': 'application/json', 'csp-auth-token':cspauthtoken}
        r = requests.get(url, headers=headers)

        components = (r.json()["component_status"])
        for component in components:
            if component["component_type"] == stagename:
                return component["status"]



def showusage():
    print("press 1 to get SDDC info\n")
    print("press 2 to get task status\n")
    print("press 3 to start migration\n")

def main(argv):
    choice = int(argv[1])
    server = os.environ['SERVER_HOST']
    print(server)
    print(choice)
    util = VmcUtil(server)
    if choice == 1:
        util.getSddcsStatus()
    elif choice == 2:
        util.getTaskStatus()
    elif choice == 3:
        util.startMigration();


#if __name__ == '__main__':
#    main(sys.argv)

util = VmcUtil('10.84.225.168')
util.startMigration()
#util.getTaskStatus('f2b1330f-2745-4a5c-8cf0-5beddbd78d46')
#util.startMigration()


