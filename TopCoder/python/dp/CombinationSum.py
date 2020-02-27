class Solution(object):
    def combinationSum(self, candidates, target):
        candidates.sort()
        n = target + 1
        mat = [[] for _ in range(n)]

        for i in range(len(candidates)):
            for j in range(1, n):
                if j < candidates[i]:
                    continue
                elif j == candidates[i]:
                    mat[j].append([candidates[i]])
                else:
                    tmp_list = mat[j - candidates[i]]
                    for el in tmp_list:
                        mat[j].append(el + [candidates[i]])

        return mat[n-1]

if __name__ == "__main__":
    arr = [2, 3, 5]
    target = 8
    sol = Solution()
    res = sol.combinationSum(arr, target)

    for i in res:
        print(i)