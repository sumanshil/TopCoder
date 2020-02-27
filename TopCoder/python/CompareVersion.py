from typing import List

class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        array1: List[str] = version1.split(".")
        array2: List[str] = version2.split(".")

        index1: int = 0
        index2: int = 0

        while index1 < len(array1) and index2 < len(array2):
            value1: int = int(array1[index1])
            value2: int = int(array2[index2])

            if value1 < value2:
                return 1
            elif value2 < value1:
                return -1
            index1 += 1
            index2 += 1

        while index1 < len(array1):
            if int(array1[index1]) != 0:
                return 1
            index1 += 1

        while index2 < len(array2):
            if int(array2[index2]) != 0:
                return -1
            index2 += 1

        return 0

if __name__ == "__main__":
    #version1: str = "1.0.1"
    #version2: str = "1"
    #version1: str = "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.00.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000"
    #version2: str  = "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000"
    version1: str = "0.1"
    version2: str = "1.1"

    sol = Solution()
    res = sol.compareVersion(version1, version2)
    print(res)
