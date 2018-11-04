
class Solution:
    def numUniqueEmails(self, emails):
        retult = set()
        for email in emails:
            arr = email.split("@")
            finalemail = arr[0].split("+")[0]
            finalemail = finalemail.replace(".", "")
            retult.add(finalemail+'@'+arr[1])

        print(retult)
        retval = set(retult)
        return len(retval)



sol = Solution()
sol.numUniqueEmails(["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"])