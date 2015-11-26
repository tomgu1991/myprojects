# get_log.py
# python3 get_log.py jabref /home/guzuxing/Desktop/jabref/
import os
import sys

def doCommand(com):
	try:
		command = "cd "+gitPath
		command = command+";"+com
		result = os.popen(command).read()
	except:
		result = "error in: "+ com
	return result

def saveToFile(str,filename):
	name = myPath+filename
	if(os.path.isfile(name)):
		os.remove(name)
	filetem = open(name,'w')
	filetem.write(str)
	filetem.close()

def getLog():
	command = git_log
	result = doCommand(command)
	# save result to file
	saveToFile(result,log_name)

def filterKeyWord():
	fileread = open(myPath+log_name)
	result = ""
	while 1:
		flag = False
		line = fileread.readline()
		line = line.lower()
		if not line:
			break
		else:
			for i in filetersWith:
				if i in line:
					flag = True
					break
			for i in filetersWithout:
				if i in line:
					flag = False
					break
		if(flag):
			result = result+line
	saveToFile(result,filter_log_name)

def getStat():
	fileRead = open(myPath+filter_log_name)
	result = ""
	while 1:
		line = fileRead.readline()
		if not line:
			break;
		str_hash = line[0:40]
		command = git_show+str_hash
		tem = doCommand(command)
		result = result+tem+";\n"
	saveToFile(result,stat_log_name)

def filterStat():
	fileRead = open(myPath+stat_log_name)
	result = ""
	while 1:
		flag = True
		lines = []
		while 1:
			line = fileRead.readline()
			if not line:
				saveToFile(result, filter_stat_log_name)
				return 1
			if line.startswith(';'):
				break
			lines.append(line)
		# change more than six files
		if(len(lines) > 6):
			continue
		tem = lines[0]
		changeLines = 0
		fileNumber = 0
		for i in range(1,len(lines)):
			# not in a test folder
			if "test" in lines[i].lower():
				continue
			# in a java file
			#print("%r" % lines[i])
			#print('.java' in lines[i].lower())
			if '.java' in lines[i].lower():
				temstr = lines[i].split('\t')
				if not temstr[0].isdigit():
					continue
				if not temstr[1].isdigit():
					continue
				add = int(temstr[0])
				dele = int(temstr[1])
				changeLines = int(temstr[0]) + int(temstr[1])
				# changeLines less than 20 and add > 0
				if changeLines < 10 and add > 0:
					tem = tem +lines[i]
					fileNumber = fileNumber + 1
		# left more than one file
		if fileNumber > 0:
			result = result + tem + ";\n"

def getChanges():
	fileRead = open(myPath+filter_stat_log_name)
	result = ""
	num = 0
	errors = []
	while 1:
		line = ""
		lines = []
		while 1:
			line = fileRead.readline()
			if not line:
				saveToFile(result,changes_diff_log_name)
				for e in errors:
					print(e)
				return 1
			if line.startswith(';'):
				break
			else:
				lines.append(line)
		str_hash = lines[0][0:40]
		num = num+1
		print(num,": get ",str_hash," changes")
		for index in range(1,len(lines)):
			name = lines[index].split('\t')[2]
			command = git_show_diff+str_hash+" -- "+gitPath+name[:-1]
#			print(command)
#			result = result+command+"\n"
			returnResult = doCommand(command)
			if returnResult.startswith('error'):
				returnResult = 'error in: '+command
#				print(returnResult)
				errors.append(returnResult)
			else:
				result = result + returnResult
				result = result + "****************************************************\n"
		result = result + ';\n\n'


# input
#gitName = "jabref"
#gitPath = "/home/guzuxing/Desktop/jabref/"
gitName = sys.argv[1]
gitPath = sys.argv[2]
if not gitPath.endswith('/'):
	gitPath = gitPath + '/'
# print(gitName, gitPath)
# mkdir gitPath/guzuxing
myPath = gitPath+"guzuxing/"
if not os.path.exists(myPath):
	command = "cd "+gitPath
	print("mkdir guzuxing")
	command = command+";"+"mkdir guzuxing"
	os.popen(command)

# command
git_log = "git log --pretty=oneline"
git_show = "git show --no-abbrev --oneline --numstat "
git_show_diff = "git show --no-abbrev --oneline "
# fileters
filetersWith = ['bug','fix','error','defect','fault']
filetersWithout = ['merge','encoding','translation','utf','html','format','default','move','javadoc','output','import','whitespace','position','indentation']
# mytemp
log_name = gitName+"_log_gzx.txt"
filter_log_name = gitName+"_filter_log_gzx.txt"
stat_log_name = gitName+"_stat_log_gzx.txt"
filter_stat_log_name = gitName +"_filter_stat_log_gzx.txt"
changes_diff_log_name = gitName+"_changes_diff_log_gzx.txt"

if(True):

	# get log
	print("get log")
	getLog()

	# filter Key words
	print("filter key work")
	filterKeyWord()

	# get stat
	print("get each commit stat")
	getStat()

	# remove !java file
	print("filter stat")
	filterStat()

	# output each file changes
	print("get each commit changes")
	getChanges()




