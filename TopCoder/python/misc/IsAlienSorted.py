def isAlienSorted(words, order):
    word2int = {c: i for i, c in enumerate(order)}
    words = [[ word2int[c] for c in w] for w in words]
    return all(w1 < w2 for w1, w2 in zip(words, words[1:]))
words = ["hello","leetcode"]
order = "hlabcdefgijkmnopqrstuvwxyz"
res = isAlienSorted(words, order)
print(res)