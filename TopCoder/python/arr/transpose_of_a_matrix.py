
def transpose_of_a_matrix(A):
    col_length = len(A[0])

    result = []

    for i in range(col_length):
        a1 = [x[i] for x in A]
        result.extend(a1)

    return result


matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
result = transpose_of_a_matrix(matrix)
print(result)