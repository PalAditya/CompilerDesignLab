import java.util.*;
import java.io.*;
class Q2
{
    public static void main(String args[])throws IOException
    {
        Q2 obj=new Q2();
        obj.go();
    }
    int a=0;
    String getChar()
    {
        a++;
        return "A"+a;
    }
    public void go()throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader("a.txt"));
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
            String token[]=right.split("[|]");
            int i;
            l=token.length;
            int j;
            ArrayList<String> al[]=new ArrayList[26];
            for(i=0;i<26;i++)
            al[i]=new ArrayList<>();
            int flag=0;
            for(i=0;i<l;i++)
            {
                if(token[i].length()>0)
                al[token[i].charAt(0)-97].add(token[i]);
                else
                {
                    //System.out.println("Flagged");
                    flag=1;
                }
            }
            String rhs="";
            for(i=0;i<26;i++)
            {
                ArrayList<String> a=al[i];          
                if(a.size()>=2)
                {
                    l=a.size();
                    int max=lcp(a.get(0),a.get(1));
                    for(j=2;j<l;j++)
                    {
                        max=(int)Math.min(max,lcp(a.get(0),a.get(j)));
                    }
                    String newChar=getChar();
                    rhs=rhs+a.get(0).substring(0,max)+newChar+"|";
                    String toExamine="";
                    //System.out.print(newChar+"->");
                    toExamine+=newChar+"->";
                    for(j=0;j<l;j++)
                    {
                        toExamine+=a.get(j).substring(max);
                        //System.out.print(a.get(j).substring(max));
                        if(j!=l-1)
                        {
                            toExamine+="|";
                            //System.out.print("|");
                        }
                    }
                    //System.out.println("ToExamine: "+toExamine);                   
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