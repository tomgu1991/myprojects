#list
if False:
	names = ['Tom','Gu']
	print("list =",names)
	print("len =",len(names))
	print("first name=",names[0])
	names.append('Gu')
	names.insert(2,'Bob')
	print("list = ",names)
	names.append("append")
	print("list append = ",names)
	names.pop(4)
	print("list = ",names)
#tuple can not change after init
if False:
	t = ('a', 'b', ['A', 'B'])
	print(t)
#dict
if False:
	d = {'Michael': 95, 'Bob': 75, 'Tracy': 85}
	print(d)
	print("d:Michael = ",d['Michael'])
	d['Tom'] = 100
	print("add Tom = ",d['Tom'])
	print("use get",d.get("Tom"))
	print("no Gu:",d.get("Gu",-1))
#set
if True:
	s = set([1, 1, 2, 2, 3, 3])
	s1 = set([2,4])
	print("s = ",s)
	print("s1 = ",s1)
	print("s & s1", s & s1)
	print("s | S1", s | s1)