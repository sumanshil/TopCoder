import csv

f = open('numbers.csv', 'r')

with f:

    reader = csv.reader(f)
    for row in reader:
        print(row)