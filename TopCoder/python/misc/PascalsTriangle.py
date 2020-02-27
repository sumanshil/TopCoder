class Solution:
    def generate(self, numRows):
        #ret_val = []
        """
        for i in range(numRows):
            if i == 0:
                ret_val.append([1])
            else:
                last_list = ret_val[len(ret_val)-1]
                new_list = []
                for j in range(len(last_list)+1):
                    num1 = 0 if j == 0 else last_list[j-1]
                    num2 = 0 if j == len(last_list) else last_list[j]
                    new_list.append(num1+num2)
                ret_val.append(new_list)
        return ret_val
        """
        ret_val = [[1]*i for i in range(1, numRows+1)]

        for i in range(2, numRows):
            for j in range(1, i):
                ret_val[i][j] = ret_val[i-1][j] + ret_val[i][j-1]
        return ret_val

if __name__ == '__main__':
    sol = Solution()
    result = sol.generate(4)
    print(result)