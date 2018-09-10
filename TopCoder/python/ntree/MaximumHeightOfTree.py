
in_height = [0]*12
out_height = [0]*12

def calculate_in(graph, index):
    children = graph[index]
    if children == 0 or len(children) == 0:
        return 0

    maxNumber = 0
    for child in children:
        height = calculate_in(graph, child)
        maxNumber = max(maxNumber, height)

    in_height[index] = maxNumber + 1
    return in_height[index]


def calculate_out(graph, index):
    children = graph[index]
    if children == 0:
        return

    maxNumber = 0
    secondMaxNumber = 0

    for child in children:
        print(child)
        in_number = in_height[child]
        if in_number > maxNumber:
            secondMaxNumber = maxNumber
            maxNumber = in_number
        elif in_number > secondMaxNumber:
            secondMaxNumber = in_number

    for child in children:
        if in_height[child] == maxNumber:
            out_height[child] = max(out_height[index], secondMaxNumber + 1) + 1
        else:
            out_height[child] = max(out_height[index], maxNumber + 1) + 1
        calculate_out(graph, child)


graph = [0]*12
graph[1] = [2, 3, 4]
graph[2] = [5, 6]
graph[3] = [7]
graph[7] = [10, 11]
graph[4] = [8, 9]
calculate_in(graph, 1)
calculate_out(graph, 1)
for i in range(0, len(out_height)):
    maxOut = max(in_height[i], out_height[i])
    print("%d : %d", i, maxOut)

for i in in_height:
    print(i)