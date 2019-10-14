import java.util.*;
import java.io.*;
class AugmentedFirstAndFollow2
{
    String fLine;
    ArrayList<String> ntMap;
    ArrayList<String> tMap;
    HashSet<String> ntCount;
    HashSet<String> tCount;
    HashMap<String,ArrayList<String>> _first;
    HashMap<String,ArrayList<String>> _follow;
    ArrayList<String> errors[][];
    AugmentedFirstAndFollow2()
    {
        fLine="";
        ntMap=new ArrayList<>();
        tMap=new ArrayList<>();
        ntCount=new HashSet<>();
        tCount=new HashSet<>();
        _first=new HashMap<>();
        _follow=new HashMap<>();
        errors=null;
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
    public void module2(ArrayList<String> arr[][],ArrayList<String> errors[][],String expr)
    {
        String tokens[]=expr.split(" ");
        Stack<String> stack=new Stack<>();
        stack.push("$");
        stack.push(fLine);
        int i,j,l;
        ArrayList<String> al=new ArrayList<>();
        for(i=0;i<tokens.length;i++)
        {
            if(i!=0 && tokens[i].equals(tokens[i-1]) && (tokens[i].charAt(0)!='('&&tokens[i].charAt(0)!=')'&&tokens[i].charAt(0)!='}'&&tokens[i].charAt(0)!='{'&&!tokens[i].equals("end")))
            {
                //System.out.println("Here");
                System.out.println("Error: Repeated token "+tokens[i]+" at position "+(i+1));//To catch errors like ++, --
                continue;
            }
            al.add(tokens[i]);
        }
        tokens=new String[al.size()];
        for(i=0;i<al.size();i++)
            tokens[i]=al.get(i);
        al.clear();
        for(i=0;i<tokens.length;i++)
        {
            if(i!=0 && tokens[i-1].equals("scan") && !tokens[i].equals("id"))
            {
                //System.out.println("Here");
                System.out.println("Error: Missing token id at position "+(i+1));
                al.add("id");//scan only followed by id
            }
            al.add(tokens[i]);
        }
        tokens=new String[al.size()];
        for(i=0;i<al.size();i++)
            tokens[i]=al.get(i);
        al.clear();
        for(i=0;i<tokens.length;i++)
        {
            if(i!=0 && tokens[i-1].equals("scan") && !tokens[i].equals("id"))
            {
                //System.out.println("Here");
                System.out.println("Error: Missing token id at position "+(i+1));
                al.add("id");//scan only followed by id
            }
            al.add(tokens[i]);
        }
        tokens=new String[al.size()];
        System.out.println("Corrected token set is: "+al);
        for(i=0;i<al.size();i++)
            tokens[i]=al.get(i);
        al.clear();
        for(i=0;i<tokens.length;)
        {
            if(stack.empty())
                break;
            String ch=stack.peek();
            String top=tokens[i];
            if(parse(arr,errors,stack,ch,top,i,tokens,0))
                i++;
            /*if(ntCount.contains(ch))
            {
                stack.pop();
                //System.out.println(ch+","+top);
                if(arr[ntMap.indexOf(ch)][tMap.indexOf(top)].size()==0)
                {
                    System.out.println("Error :(");
                    break;
                }
                String str=arr[ntMap.indexOf(ch)][tMap.indexOf(top)].get(0);
                str=str.substring(str.indexOf("->")+2).trim();
                String s[]=str.split(" ");
                //System.out.println("S: "+Arrays.toString(s));
                l=s.length;
                if(l >=2 && s[l-2].equals("#"))
                {
                    System.out.println(errors[ntMap.indexOf(ch)][tMap.indexOf(top)].get(0)+" at token "+(i+1));
                    //System.out.println(tokens[i]+","+s[l-1]);
                    tokens[i]=s[l-1];
                    l--;
                }
                for(j=l-1;j>=0;j--)
                {
                    if(s[j].equals("@") || s[j].equals("#"))
                        continue;
                    //System.out.println("Push: "+s[j]);
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
                    for(int k=0;k<tMap.size();k++)//Recursively try all
                    {
                        System.out.println("Can't be derived :(");
                        break;
                    }
                    System.exit(0);
                }
            }*/
        }
        if(stack.empty()&&i==tokens.length)
            System.out.println("Can be derived :) ");
        else
            System.out.println("Stack: "+stack+","+i+","+tokens.length);
    }
    boolean parse(ArrayList<String> arr[][],ArrayList<String> errors[][],Stack<String> stack, String ch, String top,int i, String tokens[], int in)
    {
        if(ntCount.contains(ch))
        {
            stack.pop();
            //System.out.println(ch+","+top);
            if(arr[ntMap.indexOf(ch)][tMap.indexOf(top)].size()==0)
            {
                return false;
            }
            String str=arr[ntMap.indexOf(ch)][tMap.indexOf(top)].get(0);
            str=str.substring(str.indexOf("->")+2).trim();
            String s[]=str.split(" ");
            int l=s.length,j;
            if(l >=2 && s[l-2].equals("#"))
            {
                System.out.println(errors[ntMap.indexOf(ch)][tMap.indexOf(top)].get(0)+" at token "+(i+1));
                //System.out.println(tokens[i]+","+s[l-1]);
                tokens[i]=s[l-1];
                l--;
            }
            for(j=l-1;j>=0;j--)
            {
                if(s[j].equals("@") || s[j].equals("#"))
                    continue;
                //System.out.println("Push: "+s[j]);
                stack.push(s[j]);
            }
            return false;
        }
        else
        {
            if(ch.equals(top))
            {
                 i++;
                 stack.pop();
                 if(in!=0)
                 {
                     System.out.println("Error correction was done: Replaced token "+i+" with "+ch);
                     in=0;
                 }
                 return true;
            }
            else
            {
                //return false;
                if(in>=tMap.size())
                    return false;
                //System.out.println(ch+","+top);
                /*int max=0;
                String toSend="";
                if (tMap.get(in).equals(top))//Try all
                    in++;
                while(in<tMap.size())
                {
                    if(parse(arr,errors,stack,ch,tMap.get(in),i,tokens,++in)&&i>max)
                    {
                        max=i;
                        toSend=tMap.get(in);
                        System.out.println(max+","+toSend);
                    }
                }*/
                return parse(arr,errors,stack,ch,tMap.get(in),i,tokens,++in);
            }
        }
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
        //try
        //{
            if(output)
                System.out.println("The first set: ");
            calculateFirst(hm,first,output,tCount);
            if(output)
                System.out.println("The follow set:");
            calculateFollow(hm,first,follow,output,augment);
            if(output)
                System.out.println("Table: ");
            _first.putAll(first);
            _follow.putAll(follow);
            //return null;
            return getMatrix(tCount,ntCount,first,follow,hm);
        /*}catch(Exception e)
        {
            System.out.println("Not a well defined grammar. Please check for infinite productions/left recursion,etc."+e.getMessage());
        }
        System.out.println(fLine);
        return null;*/
    }
    ArrayList<String>[][] getMatrix(HashSet<String> tCount, HashSet<String> ntCount,HashMap<String,ArrayList<String>> first, HashMap<String,ArrayList<String>> follow,
    HashMap<String,ArrayList<ArrayList<String>>> hm)
    {
        tCount.add("$");
        ntMap.addAll(ntCount);
        tMap.addAll(tCount);
        //System.out.println(ntMap);
        //System.out.println(tMap);
        ArrayList<String> arr[][]=new ArrayList[ntCount.size()][tCount.size()];
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
            //System.out.println("Considering: "+terminal+"->"+rules);
            for(ArrayList<String> rule:rules)
            {
                String ss=rule.get(0);
                //System.out.println("Umm: "+ss+","+rule);
                if(ntCount.contains(ss))
                {
                    ArrayList<String> al=first.get(ss);
                    for(String str:al)
                    {
                        ss=str.trim();
                        int row=ntMap.indexOf(terminal);
                        int col=tMap.indexOf(ss);
                        //System.out.println(row+","+col+","+terminal+"->"+rule);
                        arr[row][col].add(terminal+" -> "+join(rule," "));                        
                    }
                }
                else if(!ss.equals("@"))
                {
                    int row=ntMap.indexOf(terminal);
                    int col=tMap.indexOf(ss);
                    //System.out.println(row+","+col+","+terminal+"->"+rule);
                    arr[row][col].add(terminal+" -> "+join(rule," "));
                }
            }
            if(first.get(terminal).contains("@"))
            {
                for(String str:follow.get(terminal))
                {
                    int row=ntMap.indexOf(terminal);
                    int col=tMap.indexOf(str);
                    //System.out.println(row+","+col+","+terminal+","+str);
                    arr[row][col].add(terminal+" -> @ ");
                }
            }
        }
        fillErrorEntries(arr,ntMap,tMap);
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
        tCount.remove("$");
        return arr;
    }
    void calculateFirst(HashMap<String,ArrayList<ArrayList<String>>> hm, HashMap<String,ArrayList<String>> first,boolean output, HashSet<String> tCount)
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
        Iterator iterator=tCount.iterator();
        while(iterator.hasNext())
        {
            String s=iterator.next()+"";
            ArrayList<String> al=new ArrayList<>();
            al.add(s);
            first.put(s,al);
        }
    }
    //Should be done in a different program
    void fillErrorEntries(ArrayList<String> arr[][], ArrayList<String> ntMap, ArrayList<String> tMap)
    {
        //1:ZZ
        int i,j;
        errors=new ArrayList[ntCount.size()][tCount.size()];
        for(i=0;i<ntCount.size();i++)
        {
            for(j=0;j<tCount.size();j++)
            {
                errors[i][j]=new ArrayList<>();
            }
        }
        //Solve := and = confusions
        int equalIndex=tMap.indexOf("="),assignIndex=tMap.indexOf(":=");
        System.out.println(equalIndex+","+assignIndex);
        for(i=0;i<ntMap.size();i++)
        {
            if((arr[i][equalIndex].size()!=0&&arr[i][assignIndex].size()!=0)||(arr[i][equalIndex].size()==0&&arr[i][assignIndex].size()==0))//Both have meanings or neither have
                continue;
            if(arr[i][equalIndex].size()!=0&&arr[i][assignIndex].size()==0)//Only equality valid
            {
                arr[i][assignIndex]=new ArrayList<>(arr[i][equalIndex]);
                arr[i][assignIndex].set(0,arr[i][assignIndex].get(0).trim()+" # =");
                errors[i][assignIndex].add("Error: Should use equality rather than asignment");
            }
            else if(arr[i][equalIndex].size()==0&&arr[i][assignIndex].size()!=0)//Only assignment valid
            {
                arr[i][equalIndex]=new ArrayList<>(arr[i][assignIndex]);
                arr[i][equalIndex].set(0,arr[i][equalIndex].get(0).trim()+" # :=");
                errors[i][equalIndex].add("Error: Should use assignment rather than equality");
            }
        }
        //for
        /*for(i=0;i<tMap.size();i++)
        {
            if(tMap.get(i).equals(assignIndex))
            {
                arr[0][i]=arr[0][tMap.indexOf("=")];//Assume we mistakenly used assignment rather than equality
                errors[0][i].add("Error: Should use equality rather than asignment");
            }
            else if(arr[0][i].size()==0)
            {
                arr[0][i].add("ZZ -> @ #");
                errors[0][i].add("Error: Token "+tMap.get(i)+" can't appear in relational comparison");
            }
        }*/
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
                 //System.out.println("Considering: "+mp.getKey()+"->"+rule);
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
                                 //System.out.println("SS: "+ss);
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