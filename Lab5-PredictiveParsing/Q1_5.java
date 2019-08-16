import java.util.*;
import java.io.*;
class Q1_5
{
    String fLine="";
    ArrayList<Character> ntMap=new ArrayList<>();
    ArrayList<Character> tMap=new ArrayList<>();
    public static void main(String args[])throws IOException
    {
        FirstAndFollow obj=new FirstAndFollow();
        ArrayList<String> arr[][]=obj.module1("grammarLL_1.txt");
        if(arr==null)
        {
            System.out.println("Exiting...:(");
        }        
        System.out.print("i $ ");
        obj.module2(arr,"i $");        
        System.out.print("( i ) * ( i )* i $ ");
        obj.module2(arr,"( i ) * ( i ) * i $");
        System.out.print("i i $ ");
        obj.module2(arr,"i i $ ");
    }
}