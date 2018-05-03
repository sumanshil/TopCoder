def findPair(listX, listY, targetSum):
    indexX = 0
    indexY = len(listY)-1;
    while indexX < len(listX) and indexY >= 0:
        if listX[indexX] + listY[indexY] == targetSum:
            print ("target sum found with %d and %d " %(listX[indexX],listY[indexY]))
            indexX = indexX + 1
            indexY = indexY - 1
        elif  listX[indexX] + listY[indexY] < targetSum:
            indexX = indexX + 1
        else:
            indexY = indexY - 1

list1 = [1, 2, 3, 4, 5, 7, 11]
list2 = [2, 3, 4, 5, 6, 8, 12]
sum = 9
findPair(list1, list2, sum)
