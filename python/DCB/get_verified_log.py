# python3
# get verified log
# python3 get_verified_log.py /home/guzuxing/Desktop/jabref/guzuxing/verified_bugs.txt
import os
import sys
def saveToFile(result,filename):
	name = filename
	if(os.path.isfile(name)):
		os.remove(name)
	filetem = open(name,'w')
#	print(result)
	print("output -> ",filename)
	filetem.write(result)
	filetem.close()

# TODO buildItem
def buildResult(typeMap,typeMapCount):
	result = ''
	count = 0
	for i in typeMapCount:
		result = result + i + ":" + str(typeMapCount[i])+"\n"
		count = count + typeMapCount[i]
		print(i + ":" + str( len(typeMap[i]) ) )
	result = result + "Total:"+str(count)+"\n\n"
	for tIndex in typeMap:
		for index in typeMap[tIndex]: 
			result = result + index.getResult() + "\n"
	return result

class Item(object):

	"""docstring for Item"""
	def __init__(self,ID):
		self.paramMap = {'bugType':'',
			'commitID':0,
			'content':'',
			'fileName':'',
			'remove':[],
			'add':[],
			'ID':ID
		}

	def getResult(self):
		result = ''
		result = result + 'ID:' + str(self.paramMap['ID']) + '\n'
		result = result + 'Type:' + str(self.paramMap['bugType']) + '\n'
		result = result + 'CommitID:' + str(self.paramMap['commitID']) + '\n'
		result = result + 'Content:' + str(self.paramMap['content']) + '\n'
		result = result + 'Filename:' + str(self.paramMap['fileName']) + '\n'
		result = result + 'Remove:' + str(self.paramMap['remove']) + '\n'
		result = result + 'Add:' + str(self.paramMap['add']) + '\n'
		return result
		


#input file name
fileName = sys.argv[1]
filePath = os.path.dirname(fileName)
resultFileName = filePath+"/verified_log_gzx.txt"
# print(filePath)

reader = open(fileName)
# TODO check type
#
line = str(reader.readline()).replace('\n','')
types = line[5:].split(' ')
print(types)
typeMap = {}
typeMapCount = {}
for i in types:
	typeMap[i] = []
	typeMapCount[i] = 0
print(typeMap)
print(typeMapCount)


#item = Item(0)
#print(item.getResult())

count = 0
result = ""
while(True):
	flag = True
	lines = []
	while 1:
		line = reader.readline()
		if not line:
			result = buildResult(typeMap,typeMapCount)
			saveToFile(result,resultFileName)
			print("finish extract infomation")
			exit(0)
		if line.startswith(';'):
			break
		if line == '\n':
			continue
		lines.append(line[:-1])
#	print(lines)
	# extract each commit message
	count = count + 1
	item = Item(count)
	bugType = lines[0][2:]
#	print('bugType='+bugType)
	item.paramMap['bugType'] = bugType
	commitMessage = lines[1]
	item.paramMap['commitID'] = commitMessage[:40]
	item.paramMap['content'] = str(commitMessage).split(" ",1)[1]
	diff = str(lines[2]).split(' ')[2][2:]
	item.paramMap['fileName'] = diff
#	print(item.getResult())
	baseLine = 0
	removeLine = 0
	addLine = 0
	for index in range(6,len(lines)):
		line = str(lines[index])
#		print('line:',line)
		if line.startswith("@@"):
#			print(lines[index])
			tems = line.split("@@")[1].strip().split(" ")
			removeLine = int(tems[0][1:].split(',')[0])-1
			addLine = int(tems[1][1:].split(',')[0])-1
			baseLine = int(tems[0][1:].split(',')[0])-1
#			print('start:',removeLine,addLine)
		elif line.startswith('-') and (len(line.replace('-','').replace('\n','').strip()) > 4):
			#print(lines[index])
			removeLine += 1
#			print('remove',removeLine,addLine)
			item.paramMap['remove'].append(removeLine)
		elif line.startswith('+') and (len(line.replace('-','').replace('\n','').strip()) > 4):
			#print(lines[index])
			addLine += 1
#			print('add',removeLine,addLine)
			item.paramMap['add'].append(addLine)
		else:
			removeLine = removeLine + 1
			addLine = addLine + 1
#		if index + 1 <= len(lines)-1:
#			if lines[index+1].startswith("@@"):
#				item.paramMap['remove'].append(removeLine)
#				item.paramMap['add'].append(addLine)
	print("item ",item.paramMap['ID'],"\n"+item.getResult())
	typeMapCount[bugType] = typeMapCount[bugType] + 1
	typeMap[bugType].append(item)
#	input("press to  continue")





