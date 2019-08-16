import java.util.*;
import java.io.*;
class FirstAndFollow
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
        obj.module2(arr,"i $ ");
        obj.module2(arr,"( i ) * ( i ) * i $");
        obj.module2(arr,"i + i $ ");
    }
    int a=-1;
    String getChar()
    {
        a++;
        return ((char)('M'+a))+"";
    }
    public void module2( ArrayList<String> arr[][],String expr)
    {
        String tokens[]=expr.split(" ");
        Stack<Character> stack=new Stack<>();
        stack.push('$');
        stack.push(fLine.charAt(0));
        int i,j,l;
        for(i=0;i<tokens.length;)
        {
            if(stack.empty())
                break;
            char ch=stack.peek();
            char top=tokens[i].charAt(0);
            //System.out.println(ch+","+top+","+i);
            if(isNonTerminal(ch))
            {
                stack.pop();
                if(arr[ntMap.indexOf(ch)][tMap.indexOf(top)].size()==0)
                {
                     System.out.println("Can't be derived :(");
                     break;
                }
                String str=arr[ntMap.indexOf(ch)][tMap.indexOf(top)].get(0);
                str=str.split("->")[1];
                //System.out.println("Index: "+ntMap.indexOf(ch)+","+tMap.indexOf(top)+","+"String: "+str);
                l=str.length();
                for(j=l-1;j>=0;j--)
                {
                    if(str.charAt(j)=='@')
                        continue;
                    stack.push(str.charAt(j));
                }
            }
            else
            {
                if(ch==top)
                {
                    i++;
                    stack.pop();
                }
                else
                {
                    System.out.println("Can't be derived :(");
                    break;
                }
            }
            //System.out.println("Stack: "+stack);
        }
        if(stack.empty()&&i==tokens.length)
        System.out.println("Can be derived :) ");
    }
    public ArrayList<String>[][] module1(String filename)throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader(filename));
        String str="";
        int i,line=0;
        HashSet<Character> ntCount=new HashSet<>();
        HashSet<Character> tCount=new HashSet<>();
        HashMap<String,ArrayList<String>> hm=new HashMap<>();
        HashMap<String,ArrayList<String>> first=new HashMap<>();
        HashMap<String,ArrayList<String>> follow=new HashMap<>();
        while((str=br.readLine())!=null)//Read the string, put in map
        {
            int l=str.indexOf("->");
            String left=str.substring(0,l).trim();
            ntCount.add(left.charAt(0));
            String right=str.substring(l+2).trim();
            l=right.length();
            for(i=0;i<l;i++)
            {
                char ch=right.charAt(i);
                if(ch=='|')
                continue;
                if(isNonTerminal(ch))
                {
                    ntCount.add(ch);
                }
                else
                {
                    tCount.add(ch);
                }
            }
            String tokens[]=right.split("[|]");
            System.out.println("Tokens: "+Arrays.toString(tokens));
            if(line==0)
            {
                fLine=left;
            }
            line++;
            hm.put(left,new ArrayList<String>(Arrays.asList(tokens)));//Put all the rules in the map
        }
        br.close();
        //System.out.println(follow.get("E"));
        //System.out.println("Fline: "+fLine);
        char c='a';
        for(i=0;i<26;i++)
        {
            ArrayList<String> ch=new ArrayList<>();
            ch.add(c+"");
            first.put(c+"",ch);
            c++;
            //System.out.println(c+" ");
        }
        try
        {
            System.out.println("The first set: ");
            calculateFirst(hm,first);
            System.out.println("The follow set:");
            calculateFollow(hm,first,follow);
            System.out.println("Terminal set: ");
            tCount.remove('@');
            tCount.add('$');
            System.out.println(tCount);
            System.out.println("Non Terminal set: ");           
            System.out.println(ntCount);
            System.out.println("Table: ");
            return getMatrix(tCount,ntCount,first,follow,hm);
        }catch(Exception e)
        {
            //System.out.println("Not a well defined grammar. Please check for infinite productions/left recursion,etc.");
        }
        System.out.println(fLine);
        return null;
    }
    ArrayList<String>[][] getMatrix(HashSet<Character> tCount, HashSet<Character> ntCount,HashMap<String,ArrayList<String>> first, HashMap<String,ArrayList<String>> follow,
    HashMap<String,ArrayList<String>> hm)
    {
        ArrayList<String> arr[][]=new ArrayList[ntCount.size()][tCount.size()];
       
        ntMap.addAll(ntCount);
        tMap.addAll(tCount);
        //System.out.println(ntMap);
        //System.out.println(tMap);
        int i,j;
        for(i=0;i<ntCount.size();i++)
        {
            for(j=0;j<tCount.size();j++)
            {
                arr[i][j]=new ArrayList<>();
                /*if(i==0)
                System.out.print(tMap.get(j)+"\t\t");*/
            }
        }
        //System.out.println();
        for(Map.Entry<String, ArrayList<String>> mp:hm.entrySet())
        {
            String terminal=mp.getKey();
            ArrayList<String> rules=mp.getValue();
            //System.out.println("Considering: "+terminal+"->"+rules);
            for(String rule:rules)
            {
                char ch=rule.charAt(0);
                if(isNonTerminal(ch))
                {
                    ArrayList<String> al=first.get(ch+"");
                    for(String str:al)
                    {
                        ch=str.charAt(0);
                        int row=ntMap.indexOf(terminal.charAt(0));
                        int col=tMap.indexOf(ch);
                        //System.out.println(row+","+col+","+terminal+"->"+rule);
                        if(arr[row][col].size()==0)
                        arr[row][col].add(terminal+"->"+rule);                        
                    }
                }
                else if(ch!='@')
                {
                    int row=ntMap.indexOf(terminal.charAt(0));
                    int col=tMap.indexOf(ch);
                    //System.out.println(row+","+col+","+terminal+"->"+rule);
                    if(arr[row][col].size()==0)
                    arr[row][col].add(terminal+"->"+rule);
                }
            }
            if(first.get(terminal).contains("@"))
            {
                for(String str:follow.get(terminal))
                {
                    int row=ntMap.indexOf(terminal.charAt(0));
                    int col=tMap.indexOf(str.charAt(0));
                    //System.out.println(row+","+col+","+terminal+"->@");
                    if(arr[row][col].size()==0)
                    arr[row][col].add(terminal+"->@");
                }
            }
        }
        boolean flag=false;
        for(i=0;i<ntCount.size();i++)
        {
            //System.out.print(ntMap.get(i)+"\t\t");
            for(j=0;j<tCount.size();j++)
            {
                //System.out.print(arr[i][j]+"\t\t");
                if(arr[i][j].size()>1)
                flag=true;
            }
            //System.out.println();
        }
        ArrayList<String> pretty[][]=new ArrayList[ntCount.size()+1][tCount.size()+1];
        for(i=0;i<ntCount.size()+1;i++)
        {
            for(j=0;j<tCount.size()+1;j++)
            {
                pretty[i][j]=new ArrayList<>();
                /*if(i==0)
                System.out.print(tMap.get(j)+"\t\t");*/
            }
        }
        pretty[0][0].add("(0,0)");
        for(i=1;i<tCount.size()+1;i++)
        {
            pretty[0][i].add(tMap.get(i-1)+"");
        }
        for(i=1;i<ntCount.size()+1;i++)
        {
            pretty[i][0].add(ntMap.get(i-1)+"");
        }
        for(i=1;i<ntCount.size()+1;i++)
        {
            for(j=1;j<tCount.size()+1;j++)
            {
                pretty[i][j].addAll(arr[i-1][j-1]);
            }
        }
        PrettyPrinter printer = new PrettyPrinter(System.out);
        printer.convert(pretty);
        if(flag)
        {
            System.out.println("Not a LL(1) grammar :(");
        }
        else
        {
            System.out.println("Grammar is LL(1) :) ");
        }
        return arr;
    }
    boolean isNonTerminal(char ch)
    {
        return ch>=65&&ch<=91;
    }
    void calculateFirst(HashMap<String,ArrayList<String>> hm, HashMap<String,ArrayList<String>> first)
    {
        for(Map.Entry<String, ArrayList<String>> mp:hm.entrySet())
        {
            String terminal=mp.getKey();
            ArrayList<String> rules=mp.getValue();
            //System.out.println("Terminal,Rules: "+terminal+","+rules);
            if(first.get(terminal)!=null)//Already done;
            {
                System.out.println(terminal+"->"+unique(first.get(terminal)));
                continue;
            }
            System.out.println(terminal+"->"+unique(calculateFirstUtil(hm,first,terminal,rules)));
        }
    }
    ArrayList<String> calculateFirstUtil(HashMap<String,ArrayList<String>> hm, HashMap<String,ArrayList<String>> first, String terminal, ArrayList<String> rules)
    {
         if(first.get(terminal)!=null)
         return first.get(terminal);     
         //System.out.println("Terminal,Rules: "+terminal+","+rules);
         for(String rule:rules)
         {
             int i,l=rule.length();
             for(i=0;i<l;i++)
             {
                 char ch=rule.charAt(i);
                 ArrayList<String> a=first.get(terminal);
                 if(a==null)
                     a=new ArrayList<>();
                 if(!isNonTerminal(ch))
                 {
                     a.add(ch+"");
                     first.put(terminal,a);
                     break;
                 }
                 else
                 {
                     ArrayList<String> temp=calculateFirstUtil(hm,first,ch+"",hm.get(ch+""));
                     a.addAll(temp);
                     first.put(terminal,a);
                     if(!temp.contains("@"))
                     break;
                 }
             }
         }
         return first.get(terminal);
    }
    ArrayList<String> uniqueAndEpsilonLess(ArrayList<String> al)
    {
        HashSet<String> hs=new HashSet<>();
        hs.addAll(al);
        hs.remove("@");
        al.clear();
        al.addAll(hs);
        return al;
    }
    ArrayList<String> unique(ArrayList<String> al)
    {
        HashSet<String> hs=new HashSet<>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);
        return al;
    }
    void calculateFollow(HashMap<String,ArrayList<String>> hm, HashMap<String,ArrayList<String>> first, HashMap<String,ArrayList<String>> follow)
    {
        for(Map.Entry<String, ArrayList<String>> mp:hm.entrySet())
        {
            String terminal=mp.getKey();
            ArrayList<String> rules=mp.getValue();
            if(follow.get(terminal)!=null)//Already done;
            {
                System.out.println(terminal+"->"+uniqueAndEpsilonLess(follow.get(terminal)));
                continue;
            }
            System.out.println(terminal+"->"+uniqueAndEpsilonLess(calculateFollowUtil(hm,first,follow,terminal,0)));
        }
    }
    ArrayList<String> calculateFollowUtil(HashMap<String,ArrayList<String>> hm, HashMap<String,ArrayList<String>> first, HashMap<String,ArrayList<String>> follow, String terminal,int count)
    {
        //System.out.println("Computing: "+terminal);
        if(count>=10)
        System.exit(0);
        if(follow.get(terminal)!=null)
            return follow.get(terminal);
        for(Map.Entry<String, ArrayList<String>> mp:hm.entrySet())
        {
            ArrayList<String> rules=mp.getValue();
            for(String rule:rules)
            {
                 int i,l=rule.length();
                 if(rule.equals("@"))
                 continue;
                 for(i=0;i<l;i++)
                 {
                     char ch=rule.charAt(i);
                     //System.out.println("Considering: "+ch+",Terminal:"+terminal);
                     ArrayList<String> a=follow.get(terminal);
                     if(a==null)
                     a=new ArrayList<>();
                     follow.put(terminal,a);//No NPE
                     if(fLine.equals(terminal)&&!a.contains("$"))
                     {
                         a.add("$");
                         follow.put(terminal,a);
                         a=follow.get(terminal);
                         //System.out.println("1: "+follow);
                     }
                     if(terminal.equals(ch+"")&&i!=l-1)
                     {
                         i++;
                         ch=rule.charAt(i);
                         //System.out.println("We're in");
                         if(!isNonTerminal(ch))
                         {
                             if(ch!='@')
                             {
                                 a.add(ch+"");
                                 follow.put(terminal,a);
                                 //System.out.println("2: "+follow);
                             }
                         }
                         else
                         {
                             //System.out.println("Adding all the stuff of first: "+ch);
                             a.addAll(first.get(ch+""));
                             while(first.get(ch+"").contains("@")&&i+1<l)
                             {
                                 i++;
                                 ch=rule.charAt(i);
                                 a.addAll(first.get(ch+""));
                             }
                             if(i==l-1)//Reached end while calculting follow
                             {
                                 ///System.out.println("Reached end, key is: "+mp.getKey());
                                 ArrayList<String> temp=calculateFollowUtil(hm,first,follow,mp.getKey(),count+1);
                                 a.addAll(temp);
                             }
                             follow.put(terminal,a);
                             //System.out.println("3: "+follow);
                         }
                     }
                     else if(terminal.equals(ch+"")&&i==l-1&&!mp.getKey().equals(terminal))
                     {
                         //System.out.println("Trying to compute: "+mp.getKey());
                         ArrayList<String> temp=calculateFollowUtil(hm,first,follow,mp.getKey(),count+1);
                         a.addAll(temp);
                         follow.put(terminal,a);
                         //System.out.println("4: "+follow);
                     }
                 }
            }
        }
        return follow.get(terminal);
    }
}