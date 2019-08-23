# LR (0) Parsing: SLR method

*Compilation order* : *PrettyPrinter.java* followed by *AugmentedFirstAndFollow.java* followed by *LR_parser.java*  .

**Working of AugmentedFirstAndFollow.java**: We take the productions one by one and put them in a map.
First set is calculated as follows: 
For each production **A->x**, if there is a terminal at the start of **x**, add the terminal to the first set of **A**.
Else, recursively calculate the first set of the non-terminal and add it to first set of **A**.
Follow set is calculated as:
For each production **A->xSb**, if there is a terminal (b) following the non-terminal **(S)**, add the terminal to follow set of non-terminal.
Else, if non-terminal follows a non-terminal, add it's first to the follow set of the non-terminal. Keep doing this until the fisrt set of non-terminal has no epsilon
Add **'$'** to the follow set of start symbol
If the non-terminal in question is last symbol of a production and the generating non-terminal is not the same as it, add follow of generating non-terminal to it.

**LR_parser.java** : It defines two primary functions- *closure* and *goto*
*Closure (Set)* : Add the input set to final set. Then, for each rule in the input set, if the **dot** following it is a non-terminal, add the productions of that to the final set till no more  elements can be added.
*Goto (Set, Symbol)* : For all the pairs in the set which have the dot infront of the symbol passed alongside the set, add them to the final set by shifting the dot one position to right. Then, calculate closure for all those elements and add them to the set.
The DFA and parsing table are built following the techniques described [here]([https://www.geeksforgeeks.org/parsing-set-3-slr-clr-and-lalr-parsers/](https://www.geeksforgeeks.org/parsing-set-3-slr-clr-and-lalr-parsers/))

**PrettyPrinter.java** simply formats the table.

**Note**: The question is configured to directly run the grammar given in file Grammar.txt. If you want to change it to use GrammarQ6.txt (say), please navigate to the constructor of LR_parser.java and ensure that all commands like *terminals.add()...* and *nonTerminals.add()...* are removed and replaced by the following commands:
terminals.add("=");
terminals.add("id");
terminals.add("*");
nonTerminals.add("S");
nonTerminals.add("L");
nonTerminals.add("R");

Also, the input to be evaluated should be like this: id + ( id ) $ {Space separated with a $ at end}. Also, the input to both utils and obj should be same (Don't use Grammar.txt in one and GrammarQ6.txt in the other)