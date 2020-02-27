
def convertToTitle( n: int) -> str:

    def convert(n):
        return chr(ord('A') + n - 1)

    t = ""
    while n > 0:
        temp = (n - 1) % 26 + 1
        t = convert(temp) + t
        n = (n - 1) //26
    return t

if __name__ == "__main__":
    res = convertToTitle(26)
    print(res)