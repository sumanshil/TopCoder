from random import random

car_positions = [1, 1, 1]
time = 5

while time:
    time -= 1
    print ('')
    for i in range(len(car_positions)):
        if random() > 0.3:
            car_positions[i] += 1

        print('-' * car_positions[i])
