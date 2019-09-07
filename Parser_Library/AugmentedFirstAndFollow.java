import java.util.*;
import java.io.*;
public class AugmentedFirstAndFollow
{
    String fLine;
    ArrayList<String> ntMap;
    ArrayList<String> tMap;
    HashSet<String> ntCount;
    HashSet<String> tCount;
    HashMap<String,ArrayList<String>> _first;
    HashMap<String,ArrayList<String>> _follow;
    AugmentedFirstAndFollow()
    {
        fLine="";
        ntMap=new ArrayList<>();
        tMap=new ArrayList<>();
        ntCount=new HashSet<>();
        tCount=new HashSet<>();
        _first=new HashMap<>();
        _follow=new HashMap<>();
    }
    String join(ArrayList<String> v, String delim) 
    {
        StringBuilder ss=new StringBuilder();
        for(int i = 0; i < v.size(); ++i)
        {
            if(i != 0)
                ss.append(delim);
            ss.append(v.get(i));
        }
        return ss.toString();
    }
    String getChar(String s)
    {
        return s+"'";
    }
    public void module2(ArrayList<String> arr[][],String expr)
    {
        String tokens[]=expr.split(" ");
        Stack<String> stack=new Stack<>();
        stack.push("$");
        stack.push(fLine);
        int i,j,l;
        for(i=0;i<tokens.length;)
        {
            if(stack.empty())
                break;
            String ch=stack.peek();
            String top=tokens[i];
            if(ntCount.contains(ch))
            {
                stack.pop();
                if(arr[ntMap.indexOf(ch)][tMap.indexOf(top)].size()==0)
                {
                     System.out.println("Can't be derived :(");
                     break;
                }
                String str=arr[ntMap.indexOf(ch)][tMap.indexOf(top)].get(0);
                str=str.substring(str.indexOf("->")+2).trim();
                String s[]=str.split(" ");
                l=s.length;
                for(j=l-1;j>=0;j--)
                {
                    if(s[j].equals("@"))
                        continue;
                    stack.push(s[j]);
                }
            }
            else
            {
                if(ch.equals(top))
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
        }
        if(stack.empty()&&i==tokens.length)
            System.out.println("Can be derived :) ");
    }
    public ArrayList<String>[][] module1(String filename, boolean augment,boolean output)throws IOException
    {
        BufferedReader br=new BufferedReader(new FileReader(filename));
        String str="";
        int i,line=0,j;        
        HashMap<String,ArrayList<ArrayList<String>>> hm=new HashMap<>();
        HashMap<String,ArrayList<String>> first=new HashMap<>();
        HashMap<String,ArrayList<String>> follow=new HashMap<>();
        str=br.readLine();
        String _terminals[]=str.split(" ");
        for(i=0;i<_terminals.length;i++)
            tCount.add(_terminals[i]);
        str=br.readLine();
        String _nonTerminals[]=str.split(" ");
        for(i=0;i<_nonTerminals.length;i++)
            ntCount.add(_nonTerminals[i]);
        while((str=br.readLine())!=null)
        {
            int l=str.indexOf("->");
            String left=str.substring(0,l).trim();
            String right=str.substring(l+2).trim();
            l=right.length();
            String tokens[]=right.split("[|]");
            if(line==0)
            {
                fLine=left;
            }
            line++;
            ArrayList<ArrayList<String>> al=new ArrayList<>();
            for(i=0;i<tokens.length;i++)
            {
                String s[]=tokens[i].trim().split(" ");
                ArrayList<String> temp=new ArrayList<>();
                for(j=0;j<s.length;j++)
                    temp.add(s[j]);
                al.add(temp);
            }
            hm.put(left,al);
        }
        if(augment)
        {
            ArrayList<ArrayList<String>> al=new ArrayList<>();
            ArrayList<String> al2=new ArrayList<>();
            al2.add(fLine);
            al.add(al2);
            hm.put(fLine+"'",al);
        }
        if(output)
            System.out.println(hm);
        br.close();
        try
        {
            if(output)
                System.out.println("The first set: ");
            calculateFirst(hm,first,output);
            if(output)
                System.out.println("The follow set:");
            calculateFollow(hm,first,follow,output,augment);
            if(output)
                System.out.println("Table: ");
            _first.putAll(first);
            _follow.putAll(follow);
            return null;
            //return getMatrix(tCount,ntCount,first,follow,hm);
        }catch(Exception e)
        {
            System.out.println("Not a well defined grammar. Please check for infinite productions/left recursion,etc.");
        }
        System.out.println(fLine);
        return null;
    }
    ArrayList<String>[][] getMatrix(HashSet<String> tCount, HashSet<String> ntCount,HashMap<String,ArrayList<String>> first, HashMap<String,ArrayList<String>> follow,
    HashMap<String,ArrayList<ArrayList<String>>> hm)
    {
        ArrayList<String> arr[][]=new ArrayList[ntCount.size()][tCount.size()];       
        ntMap.addAll(ntCount);
        tMap.addAll(tCount);
        System.out.println(ntMap);
        System.out.println(tMap);
        int i,j;
        for(i=0;i<ntCount.size();i++)
        {
            for(j=0;j<tCount.size();j++)
            {
                arr[i][j]=new ArrayList<>();
            }
        }
        for(Map.Entry<String, ArrayList<ArrayList<String>>> mp:hm.entrySet())
        {
            String terminal=mp.getKey();
            ArrayList<ArrayList<String>> rules=mp.getValue();
            System.out.println("Considering: "+terminal+"->"+rules);
            for(ArrayList<String> rule:rules)
            {
                String ss=rule.get(0);
                if(ntCount.contains(ss))
                {
                    ArrayList<String> al=first.get(ss);
                    for(String str:al)
                    {
                        ss=str.trim();
                        int row=ntMap.indexOf(terminal);
                        int col=tMap.indexOf(ss);
                        System.out.println(row+","+col+","+terminal+"->"+rule);
                        if(arr[row][col].size()==0)
                        arr[row][col].add(terminal+" -> "+join(rule," "));                        
                    }
                }
                else if(!ss.equals("@"))
                {
                    int row=ntMap.indexOf(terminal);
                    int col=tMap.indexOf(ss);
                    arr[row][col].add(terminal+" -> "+join(rule," "));
                }
            }
            if(first.get(terminal).contains("@"))
            {
                for(String str:follow.get(terminal))
                {
                    int row=ntMap.indexOf(terminal);
                    int col=tMap.indexOf(str);
                    arr[row][col].add(terminal+" -> @ ");
                }
            }
        }
        boolean flag=false;
        for(i=0;i<ntCount.size();i++)
        {
            for(j=0;j<tCount.size();j++)
            {
                if(arr[i][j].size()>1)
                {
                    if(arr[i][j].size()==2&&((arr[i][j].get(0).indexOf("@")>0)||(arr[i][j].get(1).indexOf("@")>0)))
                        continue;
                    flag=true;
                }
            }
        }
        ArrayList<String> pretty[][]=new ArrayList[ntCount.size()+1][tCount.size()+1];
        for(i=0;i<ntCount.size()+1;i++)
        {
            for(j=0;j<tCount.size()+1;j++)
            {
                pretty[i][j]=new ArrayList<>();
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
    void calculateFirst(HashMap<String,ArrayList<ArrayList<String>>> hm, HashMap<String,ArrayList<String>> first,boolean output)
    {
        for(Map.Entry<String,ArrayList<ArrayList<String>>> mp:hm.entrySet())
        {
            String terminal=mp.getKey();
            ArrayList<ArrayList<String>> rules=mp.getValue();
            if(first.get(terminal)!=null)//Already done
            {
                if(output)
                    System.out.println(terminal+"->"+unique(first.get(terminal)));
                continue;
            }
            calculateFirstUtil(hm,first,terminal,rules);
            if(output)
                System.out.println(terminal+"->"+unique(calculateFirstUtil(hm,first,terminal,rules)));
        }
    }
    ArrayList<String> calculateFirstUtil(HashMap<String,ArrayList<ArrayList<String>>> hm, HashMap<String,ArrayList<String>> first, 
    String terminal, ArrayList<ArrayList<String>> rules)
    {
         if(first.get(terminal)!=null)
            return first.get(terminal);     
         for(ArrayList<String> rule:rules)
         {
             int i,l=rule.size();
             for(i=0;i<l;i++)
             {
                 String ss=rule.get(i);
                 ArrayList<String> a=first.get(terminal);                 
                 if(a==null)
                 {
                     a=new ArrayList<>();
                     first.put(terminal,a);//no NPE
                     a=first.get(terminal);
                 }
                 if(!ntCount.contains(ss))
                 {
                     a.add(ss);
                     first.put(terminal,a);
                     break;
                 }
                 else
                 {
                     if(ss.equals(terminal))//for left-recursive grammars
                        break;
                     ArrayList<String> temp=calculateFirstUtil(hm,first,ss,hm.get(ss));
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
    void calculateFollow(HashMap<String,ArrayList<ArrayList<String>>> hm, HashMap<String,ArrayList<String>> first, HashMap<String,ArrayList<String>> follow
    ,boolean output,boolean augment)
    {
        for(Map.Entry<String,ArrayList<ArrayList<String>>> mp:hm.entrySet())
        {
            String terminal=mp.getKey();
            ArrayList<ArrayList<String>> rules=mp.getValue();
            if(follow.get(terminal)!=null)//Already done
            {
                if(output)
                    System.out.println(terminal+"->"+uniqueAndEpsilonLess(follow.get(terminal)));
                continue;
            }
            calculateFollowUtil(hm,first,follow,terminal,augment);
            if(output)
                System.out.println(terminal+"->"+uniqueAndEpsilonLess(calculateFollowUtil(hm,first,follow,terminal,augment)));
        }
    }
    ArrayList<String> calculateFollowUtil(HashMap<String,ArrayList<ArrayList<String>>> hm, HashMap<String,ArrayList<String>> first, 
    HashMap<String,ArrayList<String>> follow, String terminal,boolean augment)
    {
        if(follow.get(terminal)!=null)
            return follow.get(terminal);
        for(Map.Entry<String,ArrayList<ArrayList<String>>> mp:hm.entrySet())
        {
            ArrayList<ArrayList<String>> rules=mp.getValue();
            for(ArrayList<String> rule:rules)
            {
                 int i,l=rule.size();
                 if(rule.contains("@")&&l==1)
                    continue;
                 for(i=0;i<l;i++)
                 {
                     String ss=rule.get(i);
                     ArrayList<String> a=follow.get(terminal);
                     if(a==null)
                        a=new ArrayList<>();
                     follow.put(terminal,a);//No NPE
                     if((fLine).equals(terminal)&&!a.contains("$")&&!augment)
                     {
                         a.add("$");
                         follow.put(terminal,a);
                         a=follow.get(terminal);
                     }
                     if((fLine+"'").equals(terminal)&&!a.contains("$")&&augment)
                     {
                         a.add("$");
                         follow.put(terminal,a);
                         a=follow.get(terminal);
                     }
                     if(terminal.equals(ss)&&i!=l-1)
                     {
                         i++;
                         ss=rule.get(i).trim();
                         if(!ntCount.contains(ss))
                         {
                             if(!ss.equals("@"))
                             {
                                 a.add(ss);
                                 follow.put(terminal,a);
                             }
                         }
                         else
                         {
                             a.addAll(first.get(ss));
                             while(first.get(ss).contains("@")&&i+1<l)
                             {
                                 i++;
                                 ss=rule.get(i);
                                 a.addAll(first.get(ss));
                             }
                             if(i==l-1&&!mp.getKey().equals(terminal))//Reached end while calculting follow
                             {
                                 ArrayList<String> temp=calculateFollowUtil(hm,first,follow,mp.getKey(),augment);
                                 a.addAll(temp);
                             }
                             follow.put(terminal,a);
                         }
                     }
                     else if(terminal.equals(ss)&&i==l-1&&!mp.getKey().equals(terminal))
                     {
                         ArrayList<String> temp=calculateFollowUtil(hm,first,follow,mp.getKey(),augment);
                         a.addAll(temp);
                         follow.put(terminal,a);
                     }
                 }
            }
        }
        return follow.get(terminal);
    }
}