
def flipAnImage(matrix):
    i = 0
    while i < len(matrix):
        start = 0
        end = len(matrix[i]) - 1
        while start < end:
            temp = matrix[i][start]
            matrix[i][start] = matrix[i][end]
            matrix[i][end] = temp
            start = start + 1
            end = end - 1
        i = i + 1

    i = 0
    while i < len(matrix):
        j = 0
        while j < len(matrix[i]):
            if matrix[i][j] == 1:
                matrix[i][j] = 0
            else:
                matrix[i][j] = 1
            j = j + 1
        i = i + 1
    printMatrix(matrix)


def printMatrix(matrix):
    index1 = 0
    while index1 < len(matrix):
        index2 = 0
        while index2 < len(matrix[index1]):
            print(matrix[index1][index2])
            index2 = index2 + 1
        index1 = index1 + 1
        print("\n")


if __name__ == "__main__":
    matrix = [
        [1, 1, 0],
        [1, 0, 1],
        [0, 0, 0]
    ]
    flipAnImage(matrix)