javac REtoNFA.java
java REtoNFA.java | tee output.txt
python wrapper.py 0 > resultNFA.dot
python wrapper.py 1 > resultDFA.dot
dot -Tpng resultNFA.dot -o nfa.png
dot -Tpng resultDFA.dot -o dfa.png
imgcat nfa.png
imgcat dfa.png