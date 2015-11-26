# /usr/lib/python3
# coding: utf-8
# author: guzuxing
# use buildin

from datetime import datetime
import hashlib

now = datetime.now()
print(now)
dt = datetime(2015,11,15,14,20)
print(dt)

name = 'Tom Gu'
namehash = hashlib.sha1()
namehash.update(name.encode('utf-8'))
print(namehash.hexdigest())

namehash1 = hashlib.sha1()
namehash1.update(name.encode('utf-8'))
print(namehash1.hexdigest())