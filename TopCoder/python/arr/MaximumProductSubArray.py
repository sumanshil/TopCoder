import sys
from typing import List
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        product: int = 1
        max_product = -sys.maxsize -1
        min_product = 1
        for num in nums:
            product = product * num
            min_product = min_product * num
            if product > max_product:
                max_product = product
            if min_product > max_product:
                max_product = min_product
            if product <= 0:
                product = 1
            if min_product == 0:
                min_product = 1
        return max_product


if __name__ == "__main__":
    arr = [2, 0, 7]
    sol = Solution()
    result = sol.maxProduct(arr)
    print(result)