import java.util.*;
import java.io.*;
class Q3
{
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
        BufferedReader br1=new BufferedReader(new FileReader("a.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        /**
         * Map: E ->0
         * T ->1
         * + ->2
         * E'->A->3
         * other->D->4
         * @->5
         * F->6
         * star->7
         * (->8
         * )->9
         * id->B->10
         * T'->C->11
           */ 
        /**
         * E->TA->FC->i

        or FC->(E)->(TA)->(i)
        
        (i)*i
           */  
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
                if(line==0)
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
        for(int k=0;k<keys.size();k++)
        {
            if(epsilonAble.size()+notEpsilonAble.size()==rules.size())
            break;
            String left=keys.get(k);
            ArrayList<String> al=rules.get(left);
            //System.out.println("Time for rule: "+left);
            boolean done=false;
            int count=0,epsCount=0;
            for(String s:al)
            {
                if(s.equals("@"))
                {
                    epsilonAble.add(left);
                    done=true;
                    break;
                }
                else if(containsBad(s))
                {
                    count++;
                    continue;
                }
                else
                {
                    for(int r=0;r<s.length();r++)
                    {
                        if(epsilonAble.contains(s.charAt(r)+""))
                        {
                            epsCount++;
                        }
                        else if(notEpsilonAble.contains(s.charAt(r)+""))
                        {
                            count++;
                            break;
                        }
                    }
                    if(epsCount==s.length())
                    {
                        epsilonAble.add(left);
                        done=true;
                        break;
                    }
                }
            }
            if(count==al.size())
            {
                done=true;
                notEpsilonAble.add(left);
            }
            if(!done)
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
        System.out.println("Failed DFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
    }
    boolean nonTerminal(int ch)
    {
        return ch>=65&&ch<=90;
    }
    boolean valid(String str, String target,HashSet<String> e,HashSet<String> nE)
    {
        int count=0,i,l=str.length(),l1=target.length(),j;
        for(i=0;i<l;i++)
        if(nonTerminal(str.charAt(i))&&e.contains(str.charAt(i)+""))
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
            System.out.println("Succesful DFS took: "+(((t2-t1)*(1.0))/1000)+" microsecends");
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