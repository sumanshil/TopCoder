

class Solution:
    def simplifyPath(self, path):
        paths = path.split("/")
        stack = []

        for i in range(0, len(paths)):
            path_str = paths[i]
            if path_str == '..':
                if len(stack) > 0:
                    stack.pop()
            elif path_str != '' and path_str != '.':
                stack.append(path_str)

        if len(stack) == 0:
            return "/"
        res = ""
        while len(stack) > 0:
            res = "/" + stack.pop() + res

        return res

if __name__ == "__main__":
    #str = "/../"
    str = "/a//b////c/d//././/.."
    sol = Solution()
    res = sol.simplifyPath(str)
    print(res)