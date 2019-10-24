from pprint import pprint

def isStart(i, trim_map):
	for key, value in trim_map.items():
		for val in value:
			if val[0]==i:
				return True
	return False

def isEnd(i, trim_map):
	for key, value in trim_map.items():
		for val in value:
			if val[1]==i:
				return True
	return False

def subString(Str,n): 
	
	#range_list=[]
	substring_map={}
	# Pick starting point 
	for Len in range(1,n + 1): 
		
		# Pick ending point 
		for i in range(n - Len + 1): 
			
			# Print characters from current 
			# starting point to current ending 
			# point.  
			j = i + Len - 1

			newString = ""
			for k in range(i,j + 1): 
				newString += Str[k]

			"""if newString in substring_set:
				flag=True
				for index in range(0,len(range_list)):
					if range_list[index][0]<i and range_list[index][1]>j: #Completely consumed
						flag=False
						break;
				if flag:
					range_list.append((i,j));"""

			if newString in substring_map:
				substring_map[newString].append((i,j))
			else:
				substring_map[newString]=[(i,j)]
			#print() 
	
	trim_map={}
	trim_copy={}
	for key, value in substring_map.items():
		if len(value)>1 and len(key)>2 and key[0] not in ("+","-","*","/"):
			trim_map[key]=value
			trim_copy[key]=value

	
	keyList=list(trim_map.keys())
	keyList = [str(i) for i in keyList]

	for key, value in trim_copy.items():
		for i in range(0,len(keyList)):
			if key!=keyList[i] and key in keyList[i]:
				trim_map.pop(key,'None')

	#pprint(trim_map)

	"""for i in substring_set:
		if substring_set
	print(range_list)"""

	modified_string = ""
	for i in range (0,len(Str)):
		if isStart(i,trim_map):
			if ord(Str[i])>=97 and ord(Str[i])<=122:
				modified_string+="("+Str[i]
			else:
				modified_string+=Str[i]+"("
		elif isEnd(i,trim_map):
			if ord(Str[i])>=97 and ord(Str[i])<=122:
				modified_string+=Str[i]+")"
			else:
				modified_string+=")"+Str[i]
		else:
			modified_string+=Str[i]
	
	return modified_string

s="a+b+c+e*d*x+z+a+b+c+k*x+z"
s=input()
t=len(s)
print(subString(s,t))