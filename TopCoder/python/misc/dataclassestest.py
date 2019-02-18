from dataclasses import dataclass

@dataclass
class Book(object):
     title : str
     author: str
     price: str

b1 = Book('MyTitle1', 'AuthorFirst AuthorLast', 20)
b2 = Book('MyTitle2', 'AuthorFirst AuthorLast', 25)

print(b1)
print(b2)