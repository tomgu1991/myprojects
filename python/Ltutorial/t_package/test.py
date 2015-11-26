#!/usr/bin/python3
# -*- coding: utf-8 -*-

""" a test module """

__author__ = "Tom Gu"

import sys
import abc

def test():
    args = sys.argv
    if len(args) == 1:
        print('Hello, world!')
    elif len(args) == 2:
        print('Hello, %s!' % args[1])
    else:
        print('Too many arguments!')


if __name__ == '__main__':
    test()
    print(123)
    print(abc.greeting("Tom Gu"))
