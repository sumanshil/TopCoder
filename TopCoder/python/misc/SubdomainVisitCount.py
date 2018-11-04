class Solution:
    def subdomainVisits(self, cpdomains):
        """
        :type cpdomains: List[str]
        :rtype: List[str]
        """

        dict = {}

        def updatedict(dict, key, value):
            if key in dict:
                dict[key] = int(dict[key]) + int(value)
            else:
                dict[key] = int(value)

        for domain in cpdomains:
            [visit_count, domain_name] = domain.split(" ")
            updatedict(dict, domain_name, visit_count)
            index = 0
            for char in domain_name:
                if char == '.':
                    new_domain = domain_name[index+1:]
                    updatedict(dict, new_domain, visit_count)

                index = index + 1

        return [str(v) + " " + str(k) for k, v in dict.items()]


arr = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]

solution = Solution()
ret = solution.subdomainVisits(arr)
print(ret)