
from typing import List

class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        results = []

        for num in nums:
            if len(results) == 0:
                results.append([num])
            else:
                new_results = []

                for list in results:
                    for i in range(0, len(list)+1):
                        new_result = list[:]
                        new_result.insert(i, num)
                        new_results.append(new_result)

                results = new_results
        return results

arr = [1,2,3]
sol = Solution()
res = sol.permuteUnique(arr)
print(res)