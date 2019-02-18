def median(A, B):
    m = len(A)
    n = len(B)
    if m > n:
        A, B, m, n = B, A, n, m

    imin = 0
    imax = m
    halflen = int((m + n + 1) / 2)
    while imin <= imax:
        i = int((imin + imax) / 2)
        j = halflen - i

        if i > 0 and A[i-1] > B[j]:
            imax = i - 1
        elif i < m and B[j-1] > A[i]:
            imin = i + 1
        else:
            if i == 0:
                minofleft = B[j-1]
            elif j == 0:
                minofleft = A[i-1]
            else:
                minofleft = max(A[i-1], B[j-1])
            if ((m + n) % 2) == 1:
                return minofleft

            if i == m:
                maxofright = B[j]
            elif j == n:
                maxofright = A[i]
            else:
                maxofright = min(A[i], B[j])
            return (maxofright + minofleft) / 2

A = [1, 2]
B = [3, 4, 5]
res = median(A, B)
print(res)
