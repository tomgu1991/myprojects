#!/usr/bin/python3
# -*- coding: utf-8 -*-
# __author__ = "Tom Gu"
# Student class

class Student(object):

    # __slots__ = ('name', 'age') # 用tuple定义允许绑定的属性名称
    # static
    description = "class student"
    def __init__(self, name, score):
        self.name = name
        self.score = score
        # private
        self.__ID = score + 1

    def print_score(self):
        print('%s: %s' % (self.name, self.score))

    def print_ID(self):
        print(self.name,':',self.__ID)
