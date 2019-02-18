#name_lengths = map(len, ["Mary", "Isla", "Sam"])

#for i in name_lengths:
#    print (i)

#squares = map(lambda x: x * x, [0, 1, 2, 3, 4])

#for i in squares:
#    print (i)

#import random

#names = ['Mary', 'Isla', 'Sam']
#code_names = ['Mr. Pink', 'Mr. Orange', 'Mr. Blonde']

#for i in range(len(names)):
#    names[i] = random.choice(code_names)

#import random

#names = ['Mary', 'Isla', 'Sam']

#secret_names = map(lambda x : random.choice(['Mr. Pink',
#                                             'Mr. Orange',
#                                             'Mr. Blonde']),
#                   names)

#for i in secret_names:
#    print(i)

#import functools

#sum = functools.reduce(lambda a, x: a + x, [0, 1, 2, 3, 4], 1)

#print (sum)
import functools

sentences = ['Mary read a story to Sam and Isla.',
             'Isla cuddled Sam.',
             'Sam chortled.']
#sam_count = 0
#for sentence in sentences:
#    sam_count += sentence.count('Sam')
#print(sam_count)

sam_count = functools.reduce(lambda a, x : a + x.count('Sam'), sentences, 0)

print(sam_count)

