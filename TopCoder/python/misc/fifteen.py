def wins(moves, pi):
    poss = [[0, 2, 4, 6], [1, 3, 5, 7]]
    if (pi == 0 and len(moves) == 9):
        return 1
    for i in poss[pi]:
        for j in poss[pi]:
            for k in poss[pi]:
                if (i == j or j == k or k == i):
                    continue
                if (i >= len(moves) or j >= len(moves) or k >= len(moves)):
                    continue
                if (moves[i]+moves[j]+moves[k] == 15):
                    return 1
    for m in range(1, 10):
        if (m not in moves and wins(moves+[m], not pi)):
            return 0
    return 1

def fifteen(moves):
    for m in range(1, 10):
        if (m not in moves and wins(moves+[m], 1)):
            return 'WIN '+str(m)
    return 'LOSE'

moves = [6, 3, 7, 8, 1]
print fifteen(moves)