This uses the **Thomposon's construction** method to build the NFA.  
First, we define an NFA as a three tuple: *start_vertex*, *end_vertex* and *transition_symbol* and a list of all such transitions.  
Then we build three functions: The *concat*, *kleene star* and *or*, all of which acceprt two NFAs and return a new NFA.  
To build *concat*, we accept two NFAs, create a new NFA, copy all the existing transitions of NFA 1 into the temp and copy the transitions of NFA 2 by appending it to last node of NFA 1. We then mark start node of NFA 1 as start node of temp and end end node of NFA 2 as end node of temp and return.  
Similar things are one for other two too.  
We finally use a stack to evaluate the regex.  
We then reduce the NFA to a DFA and use DFS to evaluate any given expression.  
**Input:** Use brackets and dots explicitly to indicate groups and concatenation, like **(a.b.(c|a))** and not **ab(c|a)**. Also, brackets can change the meaning significantly, like ((a.b*)|(c.a)) means a followed by any number of b's or simply ca, whereas (a.b*|(c.a)) can be interpreted as the previous one or maybe a followed by any number of b's or the fragment ca (I'll let you figure out which one :P), so ue explicit brackets too remove such ambiguity.  
Sample images are in the images directory.
