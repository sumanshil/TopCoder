
from typing import List
class Solution:
    def encode(self, list: List[str]) -> str:
        encoded_list = []
        for strn in list:
            encoded_list.append('$')
            encoded_list.append(str(len(strn)))
            encoded_list.append('$')
            encoded_list.append(strn)

        print(encoded_list)
        return ''.join(encoded_list)

    def decode(self, input: str) -> List[str]:
        result = []
        i = 0

        while i < len(input):
            print("==>"+str(i))
            length_str = []
            if input[i] == '$':
                i += 1
                while input[i] != '$':
                    length_str.append(input[i])
                    i += 1

            length_int = int(''.join(length_str))

            i += 1

            content_str = input[i: i + length_int]
            print("=====>"+content_str)
            print(content_str)
            result.append(content_str)

            i = i + length_int
        return result



if __name__ == '__main__':
    sol = Solution()
    input = ['Suman', 'Shil', 'Hello', 'World']
    encoded = sol.encode(input)
    result = sol.decode(encoded)
    print(result)