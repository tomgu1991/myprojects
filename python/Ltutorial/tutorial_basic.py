# print
if True:
	print("hello python")
#	print('The quick brown fox', 'jumps over', 'the lazy dog')
# input
if False: 
	print("input your name:")
	name = input()
	print("Welcome",name,"!")
# data type
if False:
	num_int = 1
	num_float = 12.3e-3
	str_tem = 'I\'m \"OK\"!' + "use tab \t" + "use \n new line"
	str_tem2 = r'not use transfer \t\n'
	str_tem3 = '''line1
	line2
	line3
	'''
	str_tem4 = r'''Hello,
	Lisa!'''
	b_true = True
	b_false = False
	print(num_int,num_float)
	print(str_tem)
	print(str_tem2)
	print(str_tem3)
	print(str_tem4)
	print(b_true,b_false)
# use if 
if False:
	age = int(input("your age:"))
	if age >= 18:
	    print('adult')
	elif age >= 6:
	    print('teenager')
	else:
	    print('kid')
# use for
if False:
	names = ['Michael', 'Bob', 'Tracy']
	for name in names:
		print(name)
	sum = 0
	for x in range(101):
		sum = sum + x
	print(sum)
# changable object
if True:
	l = [1,3,2]
	l.sort()
	print("l=",l)
	s = "abc"
	s.replace("a","A")
	print("s=",s)
	s2 = s.replace("a","A")
	print("s2",s2)























