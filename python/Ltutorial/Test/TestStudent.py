# ！usr/lib/python3
# utf-8
# __author__ guzuxing
# test Student
from types import MethodType

from OO.Student import Student

if __name__ == '__main__':
    bart = Student('Bart Simpson', 59)
    lisa = Student('Lisa Simpson', 87)
    bart.print_score()
    lisa.print_score()
    lisa.print_ID()
    print(type(lisa))
    print(isinstance(lisa,Student))
    # print att
    print(dir(lisa))
    print(dir(lisa.name))
    print(hasattr(lisa,'score'))
    print(hasattr(lisa,'ID'))
    print(hasattr(lisa,'print_ID'))
    print(getattr(lisa,'name'))
    print(Student.description)

    # dynamic add fun to Class
    # not useful for exist att
    def set_age(self, age):
        self.age = age
    Student.set_age = MethodType(set_age,Student)
    lisa.set_age(55)
    print(lisa.age)