def is_self_dividing(number):
    strnumber = str(number)
    isselfdividing = True
    for s in strnumber:
        intnumber = int(s)
        if intnumber == 0:
            isselfdividing = False
            break
        if number % intnumber != 0:
            isselfdividing = False
            break

    return isselfdividing


if __name__ == "__main__":
    #result = list(filter(is_self_dividing, range(1, 23)))
    result = list(x for x in range(1, 23) if is_self_dividing(x))
    print(result)