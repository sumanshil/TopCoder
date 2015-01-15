package com.topcoder.problems.round164;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1856&rd=4625
public class WhatSort
{
    class Person implements Comparable<Person>
    {
       String name;
       int    age;
       int    weight;
       Person(String name,
              int age,
              int weight)
       {
           this.name = name;
           this.age  = age;
           this.weight = weight;
       }
        @Override
        public int compareTo(Person o)
        {            
            return this.name.equals(o.name) 
                   && this.age == o.age 
                   && this.weight == o.weight ? 0 : 1;
        }
        
        @Override
            public String toString()
            {                
                return "Name :"+this.name+", Age :"+this.age +", Weight =" +this.weight;
            }
    }
    
    class NAW implements Comparator<Person>
    {

        @Override
        public int compare(Person p1, Person p2)
        {
            if ( p1.name.compareTo(p2.name) < 0 )
            {
                return -1;
            }
            else if ( p1.name.compareTo(p2.name) > 0 )
            {
                return 1;
            }
            else
            {
                if ( p1.age < p2.age )
                {
                    return -1;
                }
                else if ( p1.age > p2.age )
                {
                    return 1;
                }
                else
                {
                    if ( p1.weight > p2.weight )
                    {
                        return -1;
                    }
                    else if ( p1.weight < p2.weight )
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }
            }
        }        
    }
    
    class NWA implements Comparator<Person>
    {
        @Override
        public int compare(Person p1, Person p2)
        {
            if ( p1.name.compareTo(p2.name) < 0 )
            {
                return -1;
            }
            else if ( p1.name.compareTo(p2.name) > 0 )
            {
                return 1;
            }
            else
            {
                if ( p1.weight > p2.weight )
                {
                    return -1;
                }
                else if ( p1.weight < p2.weight )
                {
                    return 1;
                }
                else
                {
                    if ( p1.age < p2.age )
                    {
                        return -1;
                    }
                    else if ( p1.age > p2.age )
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }
            }
        }                
    }

    class AWN implements Comparator<Person>
    {
        @Override
        public int compare(Person p1, Person p2)
        {
            if ( p1.age < p2.age )
            {
                return -1;
            }
            else if ( p1.age > p2.age )
            {
                return 1;
            }
            else
            {
                if ( p1.weight > p2.weight )
                {
                    return -1;
                }
                else if ( p1.weight < p2.weight )
                {
                    return 1;
                }
                else
                {
                    if ( p1.name.compareTo(p2.name) < 0 )
                    {
                        return -1;
                    }
                    else if ( p1.name.compareTo(p2.name) > 0 )
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }                    
                }                
            }
        }                
    }

    class ANW implements Comparator<Person>
    {
        @Override
        public int compare(Person p1, Person p2)
        {
            if ( p1.age < p2.age )
            {
                return -1;
            }
            else if ( p1.age > p2.age )
            {
                return 1;
            }
            else
            {
                if ( p1.name.compareTo(p2.name) < 0 )
                {
                    return -1;
                }
                else if ( p1.name.compareTo(p2.name) > 0 )
                {
                    return 1;
                }
                else
                {
                    if ( p1.weight > p2.weight )
                    {
                        return -1;
                    }
                    else if ( p1.weight < p2.weight )
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }                
                }                                    
            }
        }                
    }

    class WNA implements Comparator<Person>
    {
        @Override
        public int compare(Person p1, Person p2)
        {
            if ( p1.weight > p2.weight )
            {
                return -1;
            }
            else if ( p1.weight < p2.weight )
            {
                return 1;
            }
            else
            {
                if ( p1.name.compareTo(p2.name) < 0 )
                {
                    return -1;
                }
                else if ( p1.name.compareTo(p2.name) > 0 )
                {
                    return 1;
                }
                else
                {
                    if ( p1.age < p2.age )
                    {
                        return -1;
                    }
                    else if ( p1.age > p2.age )
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }
            }    
        }                
    }

    class WAN implements Comparator<Person>
    {
        @Override
        public int compare(Person p1, Person p2)
        {
            if ( p1.weight > p2.weight )
            {
                return -1;
            }
            else if ( p1.weight < p2.weight )
            {
                return 1;
            }
            else
            {
                if ( p1.age < p2.age )
                {
                    return -1;
                }
                else if ( p1.age > p2.age )
                {
                    return 1;
                }
                else
                {
                    if ( p1.name.compareTo(p2.name) < 0 )
                    {
                        return -1;
                    }
                    else if ( p1.name.compareTo(p2.name) > 0 )
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }
            }    
        }                
    }

    public String sortType(String[] name, int[] age, int[] wt)
    {
        List<Person> originalList = parseAndStore(name,
                                                  age,
                                                  wt);
        int sortTypeCount = 0;
        String retVal = null;
        List<Person> newList = new ArrayList<Person>(originalList);
        Collections.sort(newList, new NAW());
        if (newList.equals(originalList))
        {
            retVal = "NAW";
            sortTypeCount ++;
        }
        
        newList = new ArrayList<Person>(originalList);
        Collections.sort(newList, new NWA());
        if (newList.equals(originalList))
        {
            retVal = "NWA";
            sortTypeCount ++;
        }

        newList = new ArrayList<Person>(originalList);
        Collections.sort(newList, new ANW());
        if (newList.equals(originalList))
        {
            retVal = "ANW";
            sortTypeCount ++;
        }

        newList = new ArrayList<Person>(originalList);
        Collections.sort(newList, new AWN());
        if (newList.equals(originalList))
        {
            retVal = "AWN";
            sortTypeCount ++;
        }

        newList = new ArrayList<Person>(originalList);
        Collections.sort(newList, new WAN());
        if (newList.equals(originalList))
        {
            retVal = "WAN";
            sortTypeCount ++;
        }

        newList = new ArrayList<Person>(originalList);
        Collections.sort(newList, new WNA());
        if (newList.equals(originalList))
        {
            retVal = "WNA";
            sortTypeCount ++;
        }

        if (sortTypeCount == 1)
        {
            return retVal;
        }
        else if (sortTypeCount > 1)
        {
            return "IND";
        }
        else
        {
            return "NOT";
        }
    }
    private List<Person> parseAndStore(String[] name, int[] age, int[] wt)
    {
        List<Person> retVal = new ArrayList<Person>();
        for (  int i = 0 ; i < name.length ; i++ ) 
        {
            retVal.add(new Person(name[i], age[i], wt[i]));
        }
        return retVal;
    }
    public static void main(String[] args)
    {
        String[] name = {"BOB","BOB","DAVE","DAVE"};
        int[]  age = {22, 35, 35, 30};
        int[] weight= {122, 122, 195,  190};
        String result = new WhatSort().sortType(name, age, weight);
        System.out.println(result);

    }

}
