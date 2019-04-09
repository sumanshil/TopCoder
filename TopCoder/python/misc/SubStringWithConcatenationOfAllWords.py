
from typing import List

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        result_list = []

        if not s or len(s) == 0:
            return result_list

        if not words or len(words) == 0:
            return result_list


        words_dict = {i: words.count(i) for i in words}
        window_length = len(words)*len(words[0])
        word_len = len(words[0])

        for i in range(0, len(words[0])):
            curr_map = {}
            j = i
            while j < i + window_length and j+word_len <= len(s):
                sub_str = s[j: j+word_len]
                curr_map[sub_str] = curr_map.get(sub_str, 0) + 1
                j = j + word_len

            left = i

            if curr_map == words_dict:
                result_list.append(left)

            while j + word_len <= len(s):
                left_str = s[left: left + word_len]
                right_str = s[j: j + word_len]

                if curr_map[left_str] and curr_map[left_str] > 1:
                    curr_map[left_str] = curr_map[left_str] - 1
                elif curr_map[left_str] and curr_map[left_str] == 1:
                    del curr_map[left_str]

                curr_map[right_str] = curr_map.get(right_str, 0) + 1
                left = left + word_len
                j = j + word_len
                if curr_map == words_dict:
                    result_list.append(left)
        return result_list

if __name__ == '__main__':
    s = "barfoothefoobarman"
    words = ["foo","bar"]
    sol = Solution()
    list = sol.findSubstring(s, words)
    for l in list:
        print(l)