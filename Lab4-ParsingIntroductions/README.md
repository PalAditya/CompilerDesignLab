
# Parsing  
  
**Q1:** It involves the following algorithm:  
1. Arrange the non-terminal symbols in order: A<sub>1</sub>, A<sub>2</sub>, A<sub>3</sub>, ..., A<sub>n</sub>  
2. For i=1 to n do:  
&emsp;For j=1 to i-1 do:  
&emsp;&emsp;*I)* replace each production of the form A<sub>i</sub>->A<sub>j</sub>Y with the productions: A<sub>i</sub>->Z<sub>1</sub>Y|Z<sub>2</sub>Y|...|Z<sub>k</sub>Y where A<sub>j</sub>->Z<sub>1</sub>|Z<sub>2</sub>|...|Z<sub>k</sub> are all &emsp;&emsp;the current  productions of A<sub>j</sub>.  
&emsp;&emsp;*II)* Eliminate the immediate left recursion among the A<sub>i</sub>  


**Q2:** It involves the following algorithm:  
&emsp;1. For each non-terminal A, find the longest prefix, say a, common to two or more of its alternatives  
&emsp;2. if (a!=epsilon) then replace all the A productions, A->ab<sub>1</sub>|ab<sub>2</sub>|ab<sub>3</sub>|...|ab<sub>n</sub>|Z, where Z is anything that does not begin with &emsp;a, with A->aY|Z and Y->b<sub>1</sub>|b<sub>2</sub>|b<sub>3</sub>|...|b<sub>n</sub>  
&emsp;Repeat the above until no common prefixes remain.  

**Q3**: It involves the basic DFS, where we expand each production and try to find a match. BFS has alo been implemented.
