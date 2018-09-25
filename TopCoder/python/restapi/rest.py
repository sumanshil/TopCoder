import requests
import json
import time
import sys,getopt
import os

class VmcUtil:
    def __init__(self, ip):
        self.ip = ip
        self.activeTaskUrl = 'http://{}:8080/vmc/api/operator/tasks?$filter=status%20eq%20%27STARTED%27'
        self.taskurl = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/tasks/{}'
        self.authurl = 'http://{}:8000/csp/am/api/login'
        self.sddcurl = 'http://{}:8080/vmc/api/operator/sddcs/'
        self.sddcspecificurl = 'http://{}:8080/vmc/api/operator/sddcs/{}'
        self.v2tprepareurlmigration = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/prepare?mode=migration'
        self.v2tprepareurlverification = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/prepare?mode=verification'
        self.getprecheckstatus = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/precheck'
        self.getcomponentstatus = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/migration/pop/networks/v1/migration/status-summary'


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
        cspauthtoken = self.getCspToken()
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

        components = (r.json()["component_status"])
        prechecksuccessful = self.precheck(components, serverip, sddcid, headers)

        if prechecksuccessful is False:
            feedbackpresent = self.checkfeedback(serverip, sddcid, headers)
            if feedbackpresent is not None:
                feedbackresponse = self.preparefeedbackresponse(feedbackpresent)
                response = self.applyfeedback(feedbackresponse)
                if response.status_code == 200 or response.status_code == 201:
                    prechecksuccessful = self.precheck(components, serverip, sddcid, headers)
            else:
                print("Aborting migration as precheck failed and no feedback found")

        if prechecksuccessful is False:
            print("Precheck failed. Aborting migration")
        #if logicalsuccess:

    def applyfeedback(self, serverip, sddcid, headers):
        feedbackuri = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/feedback'.format(serverip, sddcid)
        r = requests.post(feedbackuri, headers=headers)
        return r

    def checkfeedback(self, serverip, sddcid, headers):
        feedbackuri = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/feedback'.format(serverip, sddcid)
        r = requests.get(feedbackuri, headers=headers)
        results = []
        if r.status_code == 200:
            results = r.json()["results"]
            for r in results:
                if r["vertical"] == "EDGE" and r["sub_vertical"] == "FIREWALL":
                    results.append(r["id"])
                else:
                    return None
        return results

    def preparefeedbackresponse(self, ruleids):
        response = []
        response.append('{"response_list":[')
        for id in ruleids:
            ruleids.append('{"id":"'+id+'","action":"skip"}')
            ruleids.append(',')
        ruleids.remove(len(ruleids)-1)
        ruleids.append(']}')
        retval = ''.join(ruleids);
        return retval

    def logical(self, components, serverip, sddcid, headers):
        logcaluri = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/logicalconstructs?action=start'.format(serverip, sddcid)
        for component in components:
            print(component)
            if component['component_type'] == 'LOGICAL_CONSTRUCTS' and component['status'] == 'SUCCESS':
                print("LOGICAL_CONSTRUCTS completed successfully. continue to next phase")
                return True

        if logcaluri is not None:
            r = requests.post(logcaluri, headers=headers)
            taskId = r.json()["id"]

            taskstatus = self.taskStatus(taskId)

            if taskstatus:
                print('logcal succeeded')
                return True
            else:
                print("logical failed")
                return False


    def precheck(self, components, serverip, sddcid, headers):
        precheckurl = None
        for component in components:
            print(component)
            if component['component_type'] == 'PRECHECK' and component['status'] == 'SUCCESS':
                print("PRECHECK completed successfully. continue to next phase")
                return True
            elif 'details' in component and component['details'] == 'Rollback done':
                precheckurl = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/precheck?action=continue'.format(serverip, sddcid)
                break
            else:
                precheckurl = 'http://{}:8080/vmc/api/orgs/a67ba602-6689-450c-a743-8842ca6b032a/sddcs/{}/networking/migration/stages/precheck?action=start'.format(serverip, sddcid)
                break

        if precheckurl is not None:
            requests.post(precheckurl, headers=headers)
            return self.wait_till_success_or_failure(sddcid, serverip, 'precheck')

    def wait_till_success_or_failure(self, sddcid, serverip, stage):
        while True:
            componentstatus = self.check_status(stage, serverip, sddcid)
            if componentstatus == "SUCCESS":
                print(stage + ' succeeded')
                return True
            elif componentstatus == "FAILED":
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
    if choice == 1:
        util = VmcUtil(server)
        util.getSddcsStatus()
    elif choice == 2:
        util = VmcUtil(server)
        util.getTaskStatus()

if __name__ == '__main__':
    main(sys.argv)

#util = VmcUtil('10.2.44.233')
##util.getSddcsStatus()
#util.getTaskStatus('f2b1330f-2745-4a5c-8cf0-5beddbd78d46')
#util.startMigration()


