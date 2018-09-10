import csv

f = open('items.csv', 'r')

with f:

    reader = csv.reader(f, delimiter="|")
    for row in reader:
        for e in row:
            print(e)