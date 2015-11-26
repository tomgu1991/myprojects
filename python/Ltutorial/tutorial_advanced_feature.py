# advanced feature
# slicing
L = ['Michael', 'Sarah', 'Tracy', 'Bob', 'Jack']
print("L = ",L)
print("L[1:3]=",L[1:3])
print("L[-2:]=",L[-2:])
nums = list(range(100))
print("nums = ",nums)
print("nums[1:50:5]=",nums[1:50:5])

# literate
for name in L:
	print("welcome ",name,"!")
for x, y in [(1, 1), (2, 4), (3, 9)]:
	print(x, y)

# generate a list
L = [x*y for x in range(1, 11) for y in range(2,10,2) if x*y%3 == 0]
print("l = ",L)

# generator
g = (x*x for x in range(10))
print("g = ",g)
print("next(g)=",next(g))
def fib(max):
    n, a, b = 0, 0, 1
    while n < max:
        print(b)
        a, b = b, a + b
        n = n + 1
    return 'done'
fib(5)
def fib(max):
    n, a, b = 0, 0, 1
    while n < max:
        yield b
        a, b = b, a + b
        n = n + 1
    return 'done'
g = fib(6)
while True:
	try:
		x = next(g)
		print("g:",x)
	except StopIteration as e:
		print(e.value)
		break

# literator
