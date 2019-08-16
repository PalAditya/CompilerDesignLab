import java.util.*;
import java.io.*;
class Q2_5
{
    String fLine="";
    ArrayList<Character> ntMap=new ArrayList<>();
    ArrayList<Character> tMap=new ArrayList<>();
    static void fill(HashMap<String,Character> map1, HashMap<String,Character> map2,HashMap<Character,String> rmap1,HashMap<Character,String> rmap2)
    {
        char ch='A';
        String nonTerminals="AE BE D DL E F ES IOS IS NE P PE RE S SL T TY VL WS U W V X Y [ Z";
        String terminals="+ - * / = < > ( ) { } := ; and else end ic id if int do fc float not or print prog scan str then while";
        String s1[]=nonTerminals.split(" ");
        int i;
        for(i=0;i<s1.length;i++)
        {
            map1.put(s1[i],ch);
            rmap1.put(ch,s1[i]);
            ch++;
        }
        ch='a';
        String s2[]=terminals.split(" ");
        for(i=0;i<s2.length;i++)
        {
            if(i<26)
            {
                map2.put(s2[i],ch);
                ch++;
            }
            else if(i==26)
            {
                map2.put(s2[i],'+');
                rmap2.put('+',s2[i]);
            }
            else if(i==27)
            {
                map2.put(s2[i],'-');
                rmap2.put('-',s2[i]);
            }
            else if(i==28)
            {
                map2.put(s2[i],'*');
                rmap2.put('*',s2[i]);
            }
            else if(i==29)
            {
                map2.put(s2[i],'/');
                rmap2.put('/',s2[i]);
            }
            else if(i==30)
            {
                map2.put(s2[i],'%');
                rmap2.put('%',s2[i]);
            }
            else if(i==31)
            {
                rmap2.put('~',s2[i]);
                map2.put(s2[i],'~');
            }
        }
        map2.put("@",'@');
    }
    static void p()throws IOException
    {
        HashMap<String,Character> map1=new HashMap<>();
        HashMap<String,Character> map2=new HashMap<>();
        HashMap<Character,String> rmap1=new HashMap<>();
        HashMap<Character,String> rmap2=new HashMap<>();
        BufferedReader br=new BufferedReader(new FileReader("utt.txt"));
        String str="";
        int i;
        while((str=br.readLine())!=null)//Read the string, put in map
        {
            String tokens[]=str.split(" ");
            for(i=0;i<tokens.length;i++)
            {
                if(tokens[i].equals("->"))
                    System.out.print("-> ");
                else if(tokens[i].equals("|"))
                    System.out.print("| ");
                else if(tokens[i].charAt(0)>=65&&tokens[i].charAt(0)<=91)
                {
                    System.out.print(map1.get(tokens[i])+" ");
                }
                else
                {
                    System.out.print(map2.get(tokens[i])+" ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String args[])throws IOException
    {   
        String str="";
        FirstAndFollow obj=new FirstAndFollow();
        ArrayList<String> arr[][]=obj.module1("grammarLL_2.txt");
        if(arr==null)
        {
            System.out.println("Exiting...:(");
        }
        BufferedReader br=new BufferedReader(new FileReader("map2.txt"));
        String p="";
        while((str=br.readLine())!=null)
        {
            p=p+str+" ";
        }
        p=p+"$ ";
        System.out.print("+ $ ");
        obj.module2(arr,"+ $");        
        System.out.print("+ p $ ");
        obj.module2(arr,"+ p $ ");
        System.out.print("+ t r m p $ ");
        obj.module2(arr,"+ t r m p $");
        //The input program
        System.out.print(p+" ");
        obj.module2(arr,p);
    }
}