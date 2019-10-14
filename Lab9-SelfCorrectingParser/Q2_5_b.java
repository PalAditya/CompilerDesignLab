import java.util.*;
import java.io.*;
class Q2_5_b
{
    String fLine="";
    ArrayList<Character> ntMap=new ArrayList<>();
    ArrayList<Character> tMap=new ArrayList<>();
    public static void main(String args[])throws IOException
    {
        AugmentedFirstAndFollow2 obj=new AugmentedFirstAndFollow2();
        ArrayList<String> arr[][]=obj.module1("er1.txt",false,true);
        if(arr==null)
        {
            System.out.println("Exiting...:(");
        }        
        System.out.print("prog end $ ");
        obj.module2(arr,obj.errors,"prog end $");        
        /*String str="prog int id ; int id ; int id ; int id ; id := ic ; id := ic ; scan id print id if id = ic then id := ic ;"
                  +" else if id < ic then id := fc ; end while id > fc do id := id - ic ; end end"
                  +" while id > ic do id := id + ic ; id := fc ; if id = ic then id := ic ; end end end $";*/
        String str="prog int id ; ; scan if id := ic then id := ( ic + ic } int id ; = prog $";
        System.out.print(str+" \n");
        obj.module2(arr,obj.errors,str);   
    }
}