# git git rivision.py
# python3 get_revision.py /home/guzuxing/Desktop/jabref/guzuxing/verified_log_gzx.txt /home/guzuxing/Desktop/jabref
import os
import sys
def doCommand(com):
	try:
		result = os.popen(com).read()
	except:
		result = "error in: "+ com
	return result

def checkResultFilePath(resultFilePath):
	if os.path.exists(resultFilePath):
		print("rm ",resultFilePath)
		command = "rm -rf "+resultFilePath
		result = doCommand(command)
		print(result)
	print("mkdir get_revision:",resultFilePath)
	command = "mkdir " + resultFilePath
	result = doCommand(command)
	print(result)
	


def getAllCommitID(fileName):
	reader = open(fileName)
	results = []
	resultFolders = []
	IDs = []
	while 1:
		line = reader.readline()
		if line.startswith("CommitID"):
			results.append(line.split(':')[1].replace('\n',''))
		if line.startswith("Filename"):
			resultFolders.append(line.split(':')[1].split('/')[0])
		if line.startswith("ID:"):
			IDs.append(line.split(':')[1].replace('\n',''))
		if not line:
			return results,resultFolders,IDs

def getBaseID(gitPath):
	result = ''
	command = 'cd %s;git log --pretty=oneline -1' %gitPath
	result = doCommand(command)[0:GIT_SHORT]
	return result


def getAllRevisions(commitIDs, folders,IDs,gitPath, gitName, resultFilePath):
	# mkdir
	index = 0
	for commitID in commitIDs:
		short = commitID[0:GIT_SHORT]
		index += 1
		# cd path; mkdir id;cd id
		command = 'cd %s; mkdir %d_%s;' %(resultFilePath,int(IDs[index-1]),short)
#		print(command)
		result = doCommand(command)
		if not result:
			result = 'mkdir '+short 
		print(result)
	# cp git
	baseID = getBaseID(gitPath)
	print("current commitID :",baseID)
	total = len(commitIDs)
	index = 0
	for commitID in commitIDs:
		short = commitID[0:GIT_SHORT]
		index += 1
		print("copy revision %s [%d in %d]" % (short, index, total))
		# copy fix version
		#cd path;git reset --hard short
		#cp -a path resultFilePath/short/
		#cp -r gitName fix_short
		#rm -rf gitName
		command = 'cd %s; git reset --hard %s;' %(gitPath,short)
		command = command + 'cp -a %s/%s/ %s/%d_%s/;' %(gitPath,folders[index-1],resultFilePath,int(IDs[index-1]),short)
		command = command + 'cd %s/%d_%s;cp -r %s fix_%d_%s;rm -rf %s;' %(resultFilePath,int(IDs[index-1]),short,folders[index-1],int(IDs[index-1]),short,folders[index-1])
		print(command)
		result = doCommand(command)
		if not ('error in' in result):
			result = 'get %s fix revision' %short
		print(result)

#		input('fix')
		# copy bug version
		# cd path
		# git log --pretty=oneline -2
		command = 'cd %s;git log --pretty=oneline -2;' %gitPath
		result = doCommand(command)
		bugID = result.split('\n')[1][0:GIT_SHORT]
		print("get %s buggy revision is %s"%(short, bugID))
		#cd path;git reset --hard bugID
		#cp -a path resultFilePath/short/
		#cp -r gitName bug_short
		#rm -rf gitName
		command = 'cd %s; git reset --hard %s;' %(gitPath,bugID)
		command = command + 'cp -a %s/%s/ %s/%d_%s/;' %(gitPath,folders[index-1],resultFilePath,int(IDs[index-1]),short)
		command = command + 'cd %s/%d_%s;cp -r %s bug_%d_%s;rm -rf %s;' %(resultFilePath,int(IDs[index-1]),short,folders[index-1],int(IDs[index-1]),short,folders[index-1])
		print(command)
		result = doCommand(command)
		if not ('error in' in result):
			result = 'get %s bug revision' %bugID
		print(result)
#		input("buggy")

		# reset to baseID
		command = 'cd %s;git reset --hard %s' %(gitPath,baseID)
		result = doCommand(command)
		if not ('error in' in result):
			result = 'reset to HEAD %s'%baseID
		print(result)
#		input("reset")

#input verified_bugs.txt git_path
fileName = sys.argv[1]
gitPath = sys.argv[2]
if gitPath.endswith('/'):
	gitPath = gitPath[:-1]
git_name = gitPath[gitPath.rfind('/')+1:]
GIT_SHORT = 15
resultFilePath = gitPath[:gitPath.rfind('/')] + "/" + git_name + "_git_revision"
print(git_name,resultFilePath)

# check
checkResultFilePath(resultFilePath)
# get all commitID
commitIDs,folders,IDs = getAllCommitID(fileName) 

print("commit number :",len(commitIDs))
#print(commitIDs,folders)
# get all revisions
getAllRevisions(commitIDs,folders,IDs,gitPath,git_name,resultFilePath)