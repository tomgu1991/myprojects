# fun
print(abs(100),abs(-20),abs(12.34))
print(max(1,2,-5,6))
print(int("123"),float("12.34"),bool("True"),str(123))
a = abs
print("a = abs,a(-1)=",a(-1))
def my_abs(x):
    if not isinstance(x, (int, float)):
        raise TypeError('bad operand type')
    if x >= 0:
        #return x
        pass
    else:
        return -x
print("my_abs(5)",my_abs(5))
print("my_abs(-5)",my_abs(-5))

def power(x, n=2):
    s = 1
    while n > 0:
        n = n - 1
        s = s * x
    return s
print("power(5)",power(5))
print("power(5,3)",power(5,3))

# variable parameters
def calc(*numbers):
    sum = 0
    for n in numbers:
        sum = sum + n * n
    return sum
print("calc(1,2,3)=",calc(1,2,3))
nums = [1,2,3]
print("calc(*nums) = ",calc(*nums))

# key word fun
def person(name,age,**kw):
	print('name:',name,'age:',age,'other:',kw)
person('Bob',35,city='Beijing')
extra = {'city': 'Beijing','job':'student'}
person('Bob',35,**extra)

# check parameters
def person(name, age, **kw):
    if 'city' in kw:
        # 有city参数
        pass
    if 'job' in kw:
        # 有job参数
        pass
    print('name:', name, 'age:', age, 'other:', kw)

# must contain parameters city and job
def person(name, age, *, city, job):
    print(name, age, city, job)

# *args是可变参数，args接收的是一个tuple；
# **kw是关键字参数，kw接收的是一个dict。
# 以及调用函数时如何传入可变参数和关键字参数的语法：
# 可变参数既可以直接传入：func(1, 2, 3)，又可以先组装list或tuple，再通过*args传入：func(*(1, 2, 3))；
# 关键字参数既可以直接传入：func(a=1, b=2)，又可以先组装dict，再通过**kw传入：func(**{'a': 1, 'b': 2})。
# 使用*args和**kw是Python的习惯写法，当然也可以用其他参数名，但最好使用习惯用法。


