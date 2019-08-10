import java.util.*;
import java.io.*;
class Q2
{
    /**
     * Solution for Question 2
     * Please don't use any non-alphabetical characters ot Terminals other than A-L
     * An example input is provided in the file in2.txt
     * Please note that epsilon should be denoted by the "@" symbol
     * For the output, if it shows A->[B,Cd], that shows the production A->B|Cd
     * Please don't use terminals with >1 character
   */
    public static void main(String args[])throws IOException
    {
        Q2 obj=new Q2();
        obj.go();
    }
    int a=-1;
    String getChar()
    {
        a++;
        return ((char)('M'+a))+"";
    }
    public void go()throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader("in2.txt"));
        String str="";
        ArrayList<String> arrr=new ArrayList<>();
        while((str=br.readLine())!=null)
        {
            arrr.add(str);
        }
        for(int op=0;op<arrr.size();op++)
        {
            str=arrr.get(op);
            int l=arrr.get(op).indexOf("->");
            String left=str.substring(0,l).trim();
            String right=str.substring(l+2).trim();
            String token[]=right.split("[|]");//Split all rules
            int i;
            l=token.length;
            int j;
            ArrayList<String> al[]=new ArrayList[52];//Hash the characters so that we know when atleast first characters of two productions match
            for(i=0;i<52;i++)
            al[i]=new ArrayList<>();
            int flag=0;
            for(i=0;i<l;i++)
            {
                if(token[i].length()>0)
                    if(Character.isUpperCase(token[i].charAt(0)))
                        al[token[i].charAt(0)-65].add(token[i]);
                    else
                        al[token[i].charAt(0)-97+26].add(token[i]);
                else
                {
                    flag=1;//This to handle epsilons
                }
            }
            String rhs="";
            for(i=0;i<52;i++)
            {
                ArrayList<String> a=al[i];          
                if(a.size()>=2)
                {
                    l=a.size();
                    int max=lcp(a.get(0),a.get(1));
                    //LCP is min of all LCPs
                    for(j=2;j<l;j++)
                    {
                        max=(int)Math.min(max,lcp(a.get(0),a.get(j)));
                    }
                    String newChar=getChar();
                    rhs=rhs+a.get(0).substring(0,max)+newChar+"|";
                    String toExamine="";
                    toExamine+=newChar+"->";//Break after LCP
                    for(j=0;j<l;j++)
                    {
                        toExamine+=a.get(j).substring(max);
                        if(j!=l-1)
                        {
                            toExamine+="|";
                        }
                    }                  
                    arrr.add(toExamine);
                }
                else if(a.size()==1)
                {
                    rhs+=a.get(0)+"|";
                }
            }
            System.out.print(left+"->"+rhs.substring(0,rhs.length()-1));
            if(flag==1)
            System.out.println("|@");
            else
            System.out.println("");
        }
    }
    //Return longest common prefix among two Strings
    int lcp(String a,String b)
    {
        int i,l=a.length();
        l=(b.length()<l)?b.length():l;
        for(i=0;i<l;i++)
        if(a.charAt(i)!=b.charAt(i))
        break;
        return i;
    }
}