import sys

script, mode = sys.argv

def skipWhiteSpaces():
	global line
	while line == '\n':
		line = file.readline()

file = open('output.txt','r')

line = file.readline()
line = file.readline()

skipWhiteSpaces()

regex = line[:-1]

line = file.readline()

skipWhiteSpaces()

line = file.readline()

nfa = {}

start_nfa = 0

while line[0] == 'q':
	trans = line.split(' ')
	if trans[6] == '^\n':
		trans[6] = 'e\n'
	if (trans[0],trans[6][:-1]) not in nfa.keys():
		nfa[(trans[0],trans[6][:-1])] = []
	nfa[(trans[0],trans[6][:-1])].append(trans[2])
	line = file.readline()

trans = line.split(' ')
end_nfa = trans[4][:-1]

line = file.readline()
skipWhiteSpaces()
line = file.readline()
skipWhiteSpaces()

dfa = {}

start_dfa = 0
 
while line[0] == 'q':
	trans = line.split(' ')
	if (trans[0],trans[8][:-1]) not in dfa.keys():
		dfa[(trans[0],trans[8][:-1])] = []
	dfa[(trans[0],trans[8][:-1])].append(trans[3])
	line = file.readline()

trans = line.split(' ')
end_dfa = trans[6][:-1].split(',')

file.close()

if mode == '0':

	print "digraph finite_state_machine {"
	print "\trankdir=LR;"
	print "\tsize=\"15,10\""
	print "\tlabelloc=\"b\";"
	print "\tcolor=white;"
	print "\tfontcolor=white;"
	print "\tbgcolor=transparent;"
	print "\trankdir=LR;"
	print "\tlabel=\"Regex : {}\";".format(regex)
	print "\tnode [color=white fontcolor=white shape = doublecircle label=\"\"]; addr_{}".format(end_nfa[1:])
	print "\tnode [color=white fontcolor=white shape = circle]"
	for lhs in nfa.keys():
		rhs = nfa[lhs]
		for value in rhs:
			print "\taddr_{} -> addr_{} [color=white fontcolor=white  label = \"{}\" ];".format(lhs[0][1:],value[1:],lhs[1])

	print "\tnode [color=white fontcolor=white shape = none label=\"\"]; start"
	print "\tstart -> addr_{} [color=white fontcolor=white  label = \"start\" ]".format(start_nfa)
	print "}"

elif mode == '1':

	print "digraph finite_state_machine {"
	print "\trankdir=LR;"
	print "\tsize=\"15,10\""
	print "\tlabelloc=\"b\";"
	print "\tcolor=white;"
	print "\tfontcolor=white;"
	print "\tbgcolor=transparent;"
	print "\trankdir=LR;"
	print "\tlabel=\"Regex : {}\";".format(regex)
	for end in end_dfa:
		print "\tnode [color=white fontcolor=white shape = doublecircle label=\"\"]; addr_{}".format(end)
	print "\tnode [color=white fontcolor=white shape = circle]"
	for lhs in dfa.keys():
		rhs = dfa[lhs]
		for value in rhs:
			print "\taddr_{} -> addr_{} [color=white fontcolor=white  label = \"{}\" ];".format(lhs[0][1:],value[1:],lhs[1])

	print "\tnode [color=white fontcolor=white shape = none label=\"\"]; start"
	print "\tstart -> addr_{} [color=white fontcolor=white  label = \"start\" ]".format(start_nfa)
	print "}"