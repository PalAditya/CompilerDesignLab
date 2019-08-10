import java.util.*;
import java.io.*;
class Q3
{
    /**
     * The grammar is already there in in3.txt
     * The map shows the changed representations.
     * Map: E'->A
         * epsilon->@
         * id->i
         * T'->C
     * Solution for Question 3. For new grammar, please don't use terminals with >1 character and Terminals should be in Uppercase
     * For the output, if it shows A->[B,Cd], that shows the production A->B|Cd
     * The recursive-descent algorithm is implemented in the method call dfs()
     * Also implemented BFS version to check relative performance
     * There method valid() is overridden; the HashSets can be removed from method call to use them and it is necessary for cyclic grammars
     * where we can't find if a character can lead to epsilon production or not
    */
    public static void main(String args[])throws IOException
    {
        Q3 obj=new Q3();
        obj.go();
    }
    long t1,t2;
    boolean containsBad(String str)
    {
        int i,l=str.length();
        for(i=0;i<l;i++)
        {
            if(!nonTerminal(str.charAt(i)))
            return true;
        }
        return false;
    }
    public void go()throws IOException
    {
        BufferedReader br1=new BufferedReader(new FileReader("in3.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
        String str="";
        int i=0,l=str.length();
        HashMap<String,ArrayList<String>> rules=new HashMap<>();
        int line=0;
        Queue<String> q=new LinkedList<>();
        ArrayList<String> dfsSources=new ArrayList<>();
        HashSet<String> epsilonAble=new HashSet<>();
        HashSet<String> notEpsilonAble=new HashSet<>();
        while((str=br1.readLine())!=null)
        {
            if(str.length()>15)
            break;
            l=str.indexOf("->");
            String left=str.substring(0,l).trim();
            String right=str.substring(l+2).trim();
            String token[]=right.split("[|]");
            ArrayList<String> al=new ArrayList<>();
            for(i=0;i<token.length;i++)
            {
                if(line==0)//Save the Start rules as source of BFS/DFS
                {
                    q.add(token[i]);
                    dfsSources.add(token[i]);
                }
                line++;
                al.add(token[i]);
            }
            rules.put(left,al);
        }
        ArrayList<String> keys = new ArrayList<>(rules.keySet());
        /**
         * Find out if symbol is epsilonable, that is if it can lead to epsilon or not.
         * Rule 1: If it has an epsilon production, it is epsilonable
         * Rule 2: If it has a production with all non-terminals and they are epsilonable, it is epsilonable
         * If the above two doesn't hold, symbol is not epsilonable
           */
        for(int k=0;k<keys.size();k++)
        {
            if(epsilonAble.size()+notEpsilonAble.size()==rules.size())//We are done
            break;
            String left=keys.get(k);
            ArrayList<String> al=rules.get(left);
            boolean done=false;
            int count=0,epsCount=0;
            for(String s:al)
            {
                if(s.equals("@"))//Rule 1
                {
                    epsilonAble.add(left);
                    done=true;
                    break;
                }
                else if(containsBad(s))//This has a terminal, so rule 2 can't hold
                {
                    count++;
                    continue;
                }
                else//This rule has all nonterminals
                {
                    for(int r=0;r<s.length();r++)
                    {
                        if(epsilonAble.contains(s.charAt(r)+""))//Increase the count of epsilonable terminals
                        {
                            epsCount++;
                        }
                        else if(notEpsilonAble.contains(s.charAt(r)+""))//Rule 2 can't hold; break
                        {
                            count++;
                            break;
                        }
                    }
                    if(epsCount==s.length())//All characters were epsilonable
                    {
                        epsilonAble.add(left);
                        done=true;
                        break;
                    }
                }
            }
            if(count==al.size())//count denotes number of rules that are non-epsilonable; if we check all rules, we are done, it's not epsilonable
            {
                done=true;
                notEpsilonAble.add(left);
            }
            if(!done)//For rules like A->BC and we don't yet know if B is epsilonble or not, so save the task for later
            keys.add(left);
        }
        str=br.readLine();
        t1=System.nanoTime();
        bfs(q,str,rules,epsilonAble,notEpsilonAble);
        //Call dfs from all start productions. If it succeeds, System.exit() prevents other calls.
        for(String s:dfsSources)
        {
            t1=System.nanoTime();
            dfs(str,s,rules,new HashSet<String>(),epsilonAble,notEpsilonAble);
        }
        t2=System.nanoTime();
        System.out.println("Failed. DFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
        System.out.println("Not accepted :(");
    }
    boolean nonTerminal(int ch)
    {
        return ch>=65&&ch<=90;
    }
    //Naive version
    boolean valid(String str, String target)
    {
        int count=0,i,l=str.length(),l1=target.length(),j;
        for(i=0;i<l;i++)
        if(nonTerminal(str.charAt(i)))
        count++;
        if(target.length()<str.length()-count)//Then we have more non-Terminals than necessary; this won't work
        return false;
        return true;
    }
    //Guided version
    boolean valid(String str, String target,HashSet<String> e,HashSet<String> nE)
    {
        int count=0,i,l=str.length(),l1=target.length(),j;
        for(i=0;i<l;i++)
        if(nonTerminal(str.charAt(i))&&e.contains(str.charAt(i)+""))//Then we have more non-Terminals than necessary; this won't work
        count++;
        if(target.length()<str.length()-count)
        return false;
        return true;
    }
    void dfs(String target, String formed, HashMap<String,ArrayList<String>> rules, HashSet<String> visited,HashSet<String> e,HashSet<String> nE)
    {
        int i,l;
        if(formed.equals(target))
        {
            t2=System.nanoTime();
            System.out.println("Succesful. DFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
            System.out.println("Accepted");
            System.exit(0);
        }
        visited.add(formed);
        l=formed.length();
        for(i=0;i<l;i++)
        {
            char ch=formed.charAt(i);
            if(nonTerminal(ch))
            {
                ArrayList<String> al=rules.get(ch+"");
                for(String s:al)
                {
                    if(s.equals("@"))
                    s="";
                    String possible=formed.substring(0,i)+s+formed.substring(i+1);                    
                    if(valid(possible,target,e,nE)&&!visited.contains(possible))
                    {
                        visited.add(possible);
                        dfs(target,possible,rules,visited,e,nE);
                    }
                }
            }
        }
    }
    void bfs(Queue<String> q,String target, HashMap<String,ArrayList<String>> rules,HashSet<String> e,HashSet<String> nE)
    {
        int i,l;
        HashSet<String> visited=new HashSet<>();
        while(!q.isEmpty())
        {
            String str=q.poll();
            visited.add(str);
            if(str.equals(target))
            {
                t2=System.nanoTime();
                System.out.println("BFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
                System.out.println("Accepted");
                return;
            }
            l=str.length();
            for(i=0;i<l;i++)
            {
                char ch=str.charAt(i);
                if(nonTerminal(ch))
                {
                     ArrayList<String> al=rules.get(ch+"");
                     for(String s:al)
                     {
                         if(s.equals("@"))
                         s="";
                         String possible=str.substring(0,i)+s+str.substring(i+1);
                         if(valid(possible,target,e,nE)&&!visited.contains(possible))
                         {
                             //System.out.println("Will explore: "+str+"--->"+target+", on applying rule: "+ch+"->"+((s.length()==0)?"@":s));
                             q.add(possible);
                             visited.add(possible);
                         }
                     }
                }
            }
        }
        t2=System.nanoTime();
        System.out.println("BFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
        System.out.println("Not accepted :(");
    }   
}