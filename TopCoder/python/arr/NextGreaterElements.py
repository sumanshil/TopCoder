from array import array
class Solution:
    def nextGreaterElement(self, nums1, nums2):
        if len(nums1) == 0 or len(nums2) == 0:
            return []
        number_to_pos = {}
        next_greater = array("i")
        for i, val in enumerate(nums2):
            next_greater.append(-1)

        result = []
        number_to_pos[nums2[len(nums2)-1]] = len(nums2) - 1
        next_greater[len(nums2)-1] = -1

        outer_index = len(nums2)-2

        while outer_index >= 0:
            next_greater[outer_index] = -1
            number_to_pos[nums2[outer_index]] = outer_index
            inner_index = outer_index + 1
            while inner_index < len(nums2):
                if nums2[outer_index] < nums2[inner_index]:
                    next_greater[outer_index] = nums2[inner_index]
                    break
                elif next_greater[inner_index] > nums2[outer_index]:
                    next_greater[outer_index] = nums2[inner_index]

                inner_index = inner_index + 1
            outer_index = outer_index - 1

        for i, value in enumerate(nums1):
            result.append(next_greater[number_to_pos[value]])

        return result

if __name__ == "__main__":
    nums1 = [4,1,2]
    nums2 = [1,3,4,2]
    res = Solution().nextGreaterElement(nums1, nums2)
    print(res)