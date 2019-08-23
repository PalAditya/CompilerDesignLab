# Predictive parsing (Top-down)

*Compilation order* : PrettyPrinter.java followed by FirstAndFollow.java (or *AugmentedFirstAndFollow.java*), the other two in any order  

**Working of FirstAndFollow.java**: We take the productions one by one and put them in a map.
First set is calculated as follows: 
For each production **A->x**, if there is a terminal at the start of **x**, add the terminal to the first set of **A**.
Else, recursively calculate the first set of the non-terminal and add it to first set of **A**.
Follow set is calculated as:
For each production **A->xSb**, if there is a terminal (b) following the non-terminal **(S)**, add the terminal to follow set of non-terminal.
Else, if non-terminal follows a non-terminal, add it's first to the follow set of the non-terminal. Keep doing this until the fisrt set of non-terminal has no epsilon
Add **'$'** to the follow set of start symbol
If the non-terminal in question is last symbol of a production and the generating non-terminal is not the same as it, add follow of generating non-terminal to it.

Q1) It does nothing. Simply creates an object of FirstAndFollow.java and calls the modules to test a few Strings.
Q2) My implementation had a limitation that only characters can be terminals/non-terminals. Hence, I first used the *fill()* function in Q5_2.java to create a mapping for all the tokens to a single character. So, the transformations are:
*map.txt (actual input)* -> *Grammar_LL2.txt(parsable grammar)*
Then, a Lex program (*q3.l*) converts the given input file (*input.txt*) to tokens (*output.txt*);
Then, I use the map to transform it again: *output.txt->map2.txt*
Fianlly, the object is called and it proceeds as question 1.

**PrettyPrinter.java** simply formats the table.

You can use the rMap maps in the Q2_5.java to reverse the symbols back.

**AugmentedFirstAndFollow.java** removes the limitations of 'character-only' tokens of **FirstAndFollow.java** and can handle left-recursive grammars too. Also, it's more object-oriented.