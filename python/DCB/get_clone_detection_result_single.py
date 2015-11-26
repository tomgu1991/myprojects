# get clone detection result
# input /home/guzuxing/Desktop/findbugs/guzuxing/verified_log_gzx.txt /home/guzuxing/Desktop/findbugs_git_revision_single 5 10 1
import os
import sys
import time

def doCommand(com):
	try:
		result = os.popen(com).read()
	except:
		result = "error in: "+ com
	return result

def saveToFile(result,filename):
	name = filename
	if(os.path.isfile(name)):
		os.remove(name)
	filetem = open(name,'w')
#	print(result)
	print("output -> ",filename)
	filetem.write(result)
	filetem.close()

def saveVerifiedToEachFolder(fileName,gitPath):
	reader = open(fileName)
	bufferLine = ''
	results = ''
	singlePathName = ''
	ID = ''
	commitID = ''
	singlePathNames = []
	while 1:
		line = reader.readline()
		bufferLine += line
		if line.startswith('ID:'):
			if results.startswith('ID:'):
				print('save',singlePathName)
				saveToFile(results,gitPath+'/'+singlePathName+'/'+singlePathName+'_verified.txt')
			results = '' + line
			ID = line[3:]
		elif line.startswith('CommitID:'):
			commitID = line[9:][0:GIT_SHORT]
			results = results + line
			singlePathName =ID.replace("\n",'')+"_"+commitID
			singlePathNames.append(singlePathName)
		elif not line:
			print('save',singlePathName)
			saveToFile(results,gitPath+'/'+singlePathName+'/'+singlePathName+'_verified.txt')
			saveToFile(bufferLine,gitPath+"/total_verified_log_gzx.txt")
			return singlePathNames
		else:
			results += line


def clear(fileName,gitPath,remove_log_fileName):
	remove_log = 'remove.log'
	# save result to .java
	command = 'cd %s;find . -type f -iname "*test*" > %s' %(gitPath,gitPath+remove_log_fileName)
	result = doCommand(command)
	command = 'cd %s;find . -type f ! -iname "*.java">> %s'%(gitPath,gitPath+remove_log_fileName)
	result = doCommand(command)
	
	
	command = 'cd %s;find . -type f ! -iname "*.java" -exec rm {} \;'%(gitPath)
	result = doCommand(command)
	if not ('error in:' in result):
		result = "remove not java files in %s \n resutl in ./%s"% (gitPath,remove_log)
	print(result)
	command = 'cd %s;find . -type f -iname "*test*" -exec rm {} \;'%(gitPath)
	result = doCommand(command)
	if not ('error in:' in result):
		result = "remove test files in %s \n resutl in ./%s"% (gitPath,remove_log)
	print(result)


	# delete empty folder
	command = 'cd %s; find . -depth -empty -type d>> %s'%(gitPath,gitPath+remove_log_fileName)
	result = doCommand(command)
	command = 'cd %s;find . -depth -empty -type d -exec rmdir {} \;'%(gitPath)
	result = doCommand(command)
	if not ('error in:' in result):
		result = "remove empty folders in %s \n resutl in ./%s"% (gitPath,remove_log)
	print(result)
	# rename log
	command = 'cd %s;cp .%s %s;rm .%s' %(gitPath,remove_log_fileName,remove_log,remove_log_fileName)
	doCommand(command)

def saveResultsToFile(results,verified_clone_group_path):
	tem = ''
	for result in results:
		for line in result:
			tem += line
	saveToFile(tem, verified_clone_group_path)

def extractVerified(verifiedPath):
	v_reader = open(verifiedPath)
	line = ''
	v_line_number = []
	v_fileName = ''
	while 1:
		line = v_reader.readline()
		if not line:
			break
		if line.startswith('Remove') or line.startswith('Add'):
			line = line.split('[')[1].replace(']','').replace('\n','')
			if line=='':
				continue
			numbers = line.split(',')

			for num in numbers:
				if not int(num) in v_line_number:
					v_line_number.append(int(num))
		if line.startswith('Filename'):
			tems = line.split('/')
			v_fileName = tems[-1].replace('\n','')
	return v_fileName,v_line_number

def hasFile(tem,v_fileName,v_line_number):
	flag = False
#	print(tem)
#	input(1)
	if len(tem) < 2:
		return flag
#	input(2)
	for line in tem:
#		print('line:',line)
		if not v_fileName in line:
			continue
#		print('line :',line)
		strs = line.replace('\n','').split('\t')
		start = int(strs[4])
		end = int(strs[5])
		rangeLine = range(start-FILTERLINENUMBER,end+FILTERLINENUMBER+1)

		for number in v_line_number:
#			print("check line number ",(middle<= (number+FILTERLINENUMBER)) and ((number-FILTERLINENUMBER)<=middle))
#			input(4)
			if number in rangeLine:
#				input('get one')
				return True
	return flag

def checkResult(verifiedPath,output):
	v_fileName,v_line_number = extractVerified(verifiedPath)
	print('find in ',v_fileName,v_line_number)
	output_reader = open(output)
	line = ''
	results = []
	tem = []
	line = output_reader.readline()
	while True:
		line = output_reader.readline()
#		print(line)
#		input(1)
		if not line:
			if hasFile(tem,v_fileName,v_line_number):
				results.append(tem)
				print('find a clone group :',tem[0])
			return results
		if CLONECLASS in line:
#			input(2)
			if hasFile(tem,v_fileName,v_line_number):
#				input('add')
				results.append(tem)
				print('find a clone group :',tem[0])
#				input('continue')
			tem = []
			tem.append(line)
		else:
			tem.append(line)


def getCloneDetectionResult(singlePathNames,gitPath):
	verifiedPath = ''
	verified_clone_group_path = ''
	filePath=''
	output = ''
	logPath = ''
#	command = '%s -minblock %d -minclone %d -log %s -informat single -input %s -language %s -outformat %s -output %s'%(TOOLPATH,MINBLOCK,MINCLONE,logPath,filePath,LANGUAGE,OUTFORMAT,output)
#	print(command)
	total = len(singlePathNames)
	index = 0
	if True:
		for item in singlePathNames:
			dir = gitPath + '/' + item + '/' 
			verifiedPath = dir + item+ "_verified.txt"
			verified_clone_group_path = dir + item + '_verified_clone_group.txt'
			filePath = dir + "bug_" + item
			output = dir +  item+"_clone_detection_result.txt"
			logPath = dir + item + "_clone_detection.log"
			command = '%s -minblock %d -minclone %d -log %s -informat single -input %s -language %s -outformat %s -output %s'%(TOOLPATH,MINBLOCK,MINCLONE,logPath,filePath,LANGUAGE,OUTFORMAT,output)
			print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())) 
			index += 1
			print('get clone result in',item,'[%d of %d]'%(index,total))
	#		input('continue get clone result')
			returnTest = doCommand(command)
			if 'error in:' in returnTest:
				print(returnTest)
		
	print("finish get clone result")
	index = 0
	for item in singlePathNames:
		dir = gitPath + '/' + item + '/' 
		verifiedPath = dir + item+ "_verified.txt"
		verified_clone_group_path = dir + item + '_verified_clone_group.txt'
		output = dir +  item+"_clone_detection_result.txt"
#		input('continue extract clone group')
		index += 1
		print('extract verified clone group from bug_clone_detection_result file in',item,'[%d of %d]'%(index,total))
		results = checkResult(verifiedPath,output)
		print("save clone group to files")
		saveResultsToFile(results,verified_clone_group_path)


#input verified_bugs.txt git_revision
fileName = sys.argv[1]
gitPath = sys.argv[2]
MINBLOCK = int(sys.argv[3])
MINCLONE = int(sys.argv[4])
FILTERLINENUMBER = int(sys.argv[5])
if gitPath.endswith('/'):
	gitPath = gitPath[:-1]
print(fileName, gitPath)
remove_log_fileName = "/remove_filenames.java"
GIT_SHORT = 15
singlePathNames = []
# for iclone
TOOLPATH = '/home/guzuxing/software/iclone/iclones-0.2/iclones'
#MINBLOCK = 4
#MINCLONE = 10
#FILTERLINENUMBER = 1
LANGUAGE = 'java'
OUTFORMAT = 'text'
CLONECLASS = 'CloneClass'


# clear path
# (1)remove all test (2)remove not java file (3)remove empty folder
print('clear path')
#clear(fileName,gitPath,remove_log_fileName)
print('split verified_bugs to each folder')
#singlePathNames = saveVerifiedToEachFolder(fileName,gitPath)
#print(singlePathNames)

singlePathNames = ['1_5c2fb54e51593a0']
print('get clone detection result')
getCloneDetectionResult(singlePathNames,gitPath)