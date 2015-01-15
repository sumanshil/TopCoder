package com.topcoder.problems.round156;
//http://community.topcoder.com/stat?c=problem_statement&pm=1361&rd=4585
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordParts
{
    public int partCount(String original, String compound)
    {
        int result = recursive(original, compound);
        return result;
    }

    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int memoization(String original,
                            String compound)
    {
        if (compound.length() == 0)
            return 0;
        Set<String>  dictionary = new HashSet<String>();
        for(int i = 1 ; i < original.length() ; i++)
        {
            dictionary.add(original.substring(0, i));
        }
        
        for(int i = 1 ; i< original.length() ; i++)
        {
            dictionary.add(original.substring(i));
        }
        dictionary.add(original);
        
        recursive(original,
                  compound,
                  dictionary,
                  0);
        return map.get(0) == Integer.MAX_VALUE ? -1 : map.get(0);
        
    }
    
    
    private int recursive(String original,
                           String compound,
                           Set<String> dictionary,
                           int compoundIndex)
    {
        if (compoundIndex >= compound.length())
        {
            return 0;
        }
        else if (map.containsKey(compoundIndex))
        {
            return map.get(compoundIndex);
        }
        
        int res = Integer.MAX_VALUE;
        for(String word : dictionary)
        {
            if (compound.startsWith(word,compoundIndex))
            {
                int r = recursive(original,
                                  compound,
                                  dictionary,
                                  compoundIndex+word.length());
                if (r >= 0 &&
                    r != Integer.MAX_VALUE &&
                    r + 1 < res)
                {
                    res = r+1;
                }
            }
        }
        map.put(compoundIndex, res);
        return res;
    }


    private int recursive(String original,
                          String compound)
    {
        if (original.startsWith(compound)
                || original.endsWith(compound))
        {
            System.out.println(compound);
            return 1;
        }
        else if(compound.length() == 1)
        {
            if (original.startsWith(compound)
                    || original.endsWith(compound))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        int res = -1;
        for(int i = compound.length() ; i >=0 ; i-- )
        {
            String str = compound.substring(0,i);
            if (i < compound.length())
               res = recursive(original, compound.substring(i));
            else
            {
                if (original.startsWith(str)
                        || original.endsWith(str))
                {
                    System.out.println(str);
                    return 1;
                }
            }
            if (res > 0)
            {
                if (original.startsWith(str)
                        || original.endsWith(str))
                {
                    System.out.println(str);
                    return res+1;
                    
                }
                
            }
        }
        return res;
    }

    public int solution2(String original,
                         String compound)
    {
        int n1 = compound.length();
        int n2 = original.length();
        int[] arr = new int[n1+1];
        arr[0] = 0;
        for(int i = 1 ; i <= n1 ; i++ )
        {
            int c = Integer.MAX_VALUE;
            for(int j = 0 ; j < i ; j++)
            {
                if (arr[j] > -1 && arr[j]+1 < c)
                {
                    int d = original.indexOf(compound.substring(j, i));
                    if (d == 0)
                    {
                        c = arr[j]+1;
                    }
                    else
                    {
                        d = original.indexOf(compound.substring(j,i), n2 - (i-j));
                        if (d > 0 &&  d == (n2 - (i-j)))
                        {
                            c = arr[j]+1;
                        }
                    }
                }
            }
            if (c == Integer.MAX_VALUE)
            {
                arr[i] = -1;
            }
            else
            {
                arr[i] = c;
            }
            
        }
        return arr[n1];
        
    }
    
    
    public static void main(String[] args)
    {
        String original = "ABCXABCYABC";
        String compound = "ABCYABCA";
        int result = new WordParts().solution2(original, compound);
        System.out.println(result);

    }

}
