from typing import List

class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        start_row = 0
        end_row = len(matrix) - 1
        start_col = 0
        end_col = len(matrix[0]) - 1

        return_value = []
        while start_row <= end_row or start_col <= end_col:
            current_col = start_col
            if current_col <= end_col:
                while current_col <= end_col:
                    return_value.append(matrix[start_row][current_col])
                    current_col += 1
            else:
                break

            current_row = start_row + 1
            if current_row <= end_row:
                while current_row <= end_row:
                    return_value.append(matrix[current_row][end_col])
                    current_row += 1
            else:
                break

            current_col = end_col - 1
            if current_col >= start_col:
                while current_col >= start_col:
                    return_value.append(matrix[end_row][current_col])
                    current_col -= 1
            else:
                break

            current_row = end_row - 1
            if current_row > start_row:
                while current_row > start_row:
                    return_value.append(matrix[current_row][start_col])
                    current_row -= 1
            else:
                break

            start_col += 1
            end_col -= 1
            start_row += 1
            end_row -= 1

        return return_value


if __name__ == '__main__':
    matrix = [
        [ 1, 2, 3 ],
        [ 4, 5, 6 ],
        [ 7, 8, 9 ]
    ]
    sol = Solution()
    res = sol.spiralOrder(matrix)
    print(res)