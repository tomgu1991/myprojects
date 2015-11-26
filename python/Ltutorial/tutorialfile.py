# !usr/lib/python3
# coding:utf-8
# author guzuxing
# use file

import os

osName = os.name
print(osName)
uName = os.uname()
print(uName)
env = os.environ
print(env)
abPath = os.path.abspath('.')
print(abPath)
print(os.path.join(os.path.abspath('.'),'OO'))
parentPath = os.path.split(abPath)
print(parentPath)
print([x for x in os.listdir('.') if os.path.isdir(x)])
print([x for x in os.listdir('.') if os.path.isfile(x) and os.path.splitext(x)[1]=='.py'])