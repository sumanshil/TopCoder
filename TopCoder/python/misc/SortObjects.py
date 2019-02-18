
class Employee:
    def __init__(self, name, age):
        self.name = name
        self.age = age


e1 = Employee("Suman", 36)
e2 = Employee("Ajay", 35)
e3 = Employee("Deepak", 38)

list = [e1, e2, e3]
list.sort(key= lambda x: x.name)
for obj in list:
    print(obj.name)