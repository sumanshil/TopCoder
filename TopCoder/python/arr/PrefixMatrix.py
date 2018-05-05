
def prefixArr(matrix):
    rowLength = len(matrix)
    colLength = len(matrix[0])

    for i in range(1, rowLength):
        matrix[i][0] += matrix[i-1][0]

    for i in range(1, colLength):
        matrix[0][i] += matrix[0][i-1]


    for i in range(1, rowLength):
        for j in range(1, colLength):
            matrix[i][j] += matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1]

    for i in range(0, rowLength):
        for j in range(0, colLength):
            print(matrix[i][j], ",")
        print("\n")


matrix = [
      [10, 20, 30],
      [5, 10, 20],
      [2, 4, 6]
    ]
prefixArr(matrix)
