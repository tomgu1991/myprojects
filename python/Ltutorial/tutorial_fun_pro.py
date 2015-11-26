# use fun as parameter
def add(x,y,f):
	return f(x)+f(y)
print("add(x,y,f)=",add(1,-2,abs))

# use map
# map need a fun and calculate the fun on the every element in the literator
# and the result is map object, lazy result
def f(x):
	return x*x
r = list(map(f,[1,2,4]))
print(r)

# use reduce
# reduce work on list one by one
# reduce(f, [x,y,z]) = f(f(x,y),z)
from functools import reduce
def fn(x,y):
	return x*10+y
result = reduce(fn,[1,3,4,5])
print(result)

def char2num(s):
	return {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9}[s]
result = reduce(fn,map(char2num,'12345'))
print(result)

#use filter
# return a list [3,5,7]
def _odd_iter():
	n = 1
	while True:
		n = n+2
		yield n
print(next(_odd_iter()))
#return a fun = lambda x:x%n > 0
def _not_divisible(n):
	return lambda x:x%n > 0
print(_not_divisible(5)(10))
def primes():
	yield 2
	it = _odd_iter()
	while True:
		n = next(it)
		yield n
		it = filter(_not_divisible(n),it)
for n in primes():
	if n< 1000:
		print(n)
	else:
		print("finish")
		break

# sorted
print(sorted(['bob', 'about', 'Zoo', 'Credit'], key=str.lower, reverse=True))

# return fun
def lazy_sum(*args):
	def sum():
		ax = 0
		for n in args:
			ax = ax + n
		return ax
	return sum
f = lazy_sum(1,3,4)
print(f())

# lambda
f = lambda x: x * x
print(f)
print(f(5))

# decorator
import datetime
def log(text):
	def decorator(func):
		def wrapper(*args, **kw):
			print("%s %s():" % (text,func))
			return func(*args, **kw)
		return wrapper
	return decorator
@log("execute")
def mynow():
	print(datetime.datetime.now().time())
mynow()

# partial
import functools
int2 = functools.partial(int,base=2)
print(int2('10010'))

max2 = functools.partial(max,10)
print(max2(3,4,5))
print(max2(3,4,15))