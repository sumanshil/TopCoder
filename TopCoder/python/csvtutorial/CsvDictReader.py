import csv

f = open('values.csv', 'r')

with f:
    reader = csv.DictReader(f)
    for row in reader:
        print(row)