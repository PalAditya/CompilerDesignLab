# Assembly and Source Code Link Exploration

You can get the source code by using **gcc -S filename**

**Note**: ssort is an 8085 assembly code for selection sort, included for comparison purposes only.

Explanation for *lab8.s (basic)*:

| Line | Work |
| ------------- | ------------- |
Lines 1-3 | Definition
Line 5  | The .ascii directive places the characters in string into the object module at the current location
Line 10 | scl 2 means storage class 2(external storage class), and 32 refers to a function
Line 11 | main start
Line 17 | assigns the stack pointer to ebp register
Line 21 | main is called
Line 22 to 32| We assign elements of the array to memory locations, based on offset from esp pointer

It then moves to the for loops

|Line | Work |
| ------------- | ------------- |
L2| It simply compares if arr[j]>max. The jl (jump on less) command allows it to not enter the if section,as it then goes to L8 or else it goes to L9
L8| It sets index to -1 and max to arr[l-i-1] {first two lines of loop} before jumping to L3 (j loop)
L3|Lines 58 to 62 perform the check if arr[j]>=max (by the subtract long, or subl command) and does a jump in that case to L5. Line 64-65 checks if index is -1, in which case L6 is called {L6's function described below}. Finally, L7 adds one to loop index (j++)
L5|Does the two operations: {max=arr[j];index=j;}
L9| Handles the end of program. It calls L10 repeatedly (boundary check done by jl command) to print the array and then returns
L4| Handles increment for i (outer) loop
L6| It handles the swap part {temp=arr[l-i-1];arr[l-i-1]=arr[index];arr[index]=temp;}

**Note2**: add.s is much easier to understand. In lines 20-24, we first assign 2 and 3 to memory locations and then load them to registers (*eax* and *edx*). Addition is done with addl command, printf call is then made to output the value and then program exits.
