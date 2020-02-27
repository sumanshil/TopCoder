class Solution(object):
    def searchMatrix(self, matrix, target):
        l = 0
        r = len(matrix)-1
        col_length = len(matrix[0])-1

        selected_row = -1
        while l <= r:
            mid = int((l+r) / 2)

            if matrix[mid][0] <= target and matrix[mid][col_length] >= target:
                selected_row = mid
                break
            elif matrix[mid][-1] < target:
                l = mid+1
            elif matrix[mid][0] > target:
                r = mid-1

        if selected_row == -1:
            return False

        l = 0
        r = len(matrix[0]) - 1

        while l <= r:
            mid = int((l + r) / 2)
            if matrix[selected_row][mid] == target:
                return True
            elif matrix[selected_row][mid] > target:
                r = mid - 1
            else:
                l = mid + 1

        return False

if __name__ == '__main__':
    sol = Solution()
    matrix = [
        [1,   3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 50]
    ]
    target = 3
    res = sol.searchMatrix(matrix, target)
    print(res)