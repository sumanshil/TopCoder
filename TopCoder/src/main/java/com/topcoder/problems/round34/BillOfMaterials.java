package com.topcoder.problems.round34;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//http://community.topcoder.com/stat?c=problem_statement&pm=184&rd=4005
public class BillOfMaterials {
	
	static class Component implements Comparable<Component>
	{
	   String name;
	   int quantity;
	   
	   public Component(String name, int quantity)
	   {
		   this.name     = name;
		   this.quantity = quantity;
	   }

	   public int compareTo(Component o) 
	   {
           if ( o.name.compareTo(this.name) > 0 )
           {
        	   return -1;
           }
           else if (o.name.compareTo(this.name) < 0)
           {
        	   return 1;
           }
		   return 0;
	   }
	   
	   public String toString()
	   {
		   return name+":"+quantity;
	   }
	}
	
	Map<String, Component> result = new HashMap<String, Component>();
	public String[] explode(String calcProduct, int quantity, String[] BOM)
	{
		Map<String, List<Component>> map = prepareProductList(BOM);
		calculateRecursive(calcProduct, quantity, map);
		Set<Component> set = new TreeSet<Component>();
		for(Map.Entry<String, Component> entry : result.entrySet())
		{
			set.add(entry.getValue());
		}
	    String[] retVal = new String[result.size()];
	    int index = 0;
	    for(Component comp : set)
	    {
	    	retVal[index++] = comp.toString();
	    }
	    return retVal;
	}
	
	private void calculateRecursive(String calcProduct,
			                        int quantity,
			                        Map<String, List<Component>> map)
	{
        if (!map.containsKey(calcProduct))
        {
        	if (!result.containsKey(calcProduct))
        	{
        	    result.put(calcProduct, new Component(calcProduct, quantity));
        	}
        	else
        	{
        		Component comp = result.get(calcProduct);
        		//result.put(calcProduct, new Component(calcProduct,comp.quantity+quantity));
        		comp.quantity += quantity;
        	}
        	return;
        }
        
        List<Component> components = map.get(calcProduct);
        for(Component comp : components)
        {
        	calculateRecursive(comp.name,
        			           comp.quantity*quantity,
        			           map);
        }
		
	}

	private Map<String, List<Component>> prepareProductList(String[] BOM) 
	{
		Map<String, List<Component>> map = new HashMap<String,
		                                               List<Component>>();
        for(String str : BOM)
        {
        	String[] array        = str.split("\\s+");
        	String productName    = array[0];
        	String componentList  = array[1];
        	List<Component> list  = getComponentList(componentList);
        	map.put(productName, list);
        }
		return map;
	}

	private List<Component> getComponentList(String componentList)
	{
		String[]          array = componentList.split(",");
		List<Component>   list  = new ArrayList<Component>();
		for(String str : array)
		{
	         String[] arr      = str.split(":");
	         String   name     = arr[0];
	         int      quantity = Integer.parseInt(arr[1]);
	         list.add(new Component(name, quantity));
		}
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
        String[] result = new BillOfMaterials().explode("KBd", 657, new String[] {"PC Cse:1,Mon:1,Kbrd:1,Mse:1,PwrCbl:2", "KBd Ks:104,Sprngs:104,KBdCbl:1,Wrs:20,KCse:1", "KCse Legs:2,KBox:1", "Mse MseWr:1,MseBall:1,MseCse:1", "MseCse MseKs:3,MseBox:1", "Mon MonCbl:2,MonBttns:10,Things:1000", "Cse CPU:1,HdDk:1,SysBd:1,PwrS:1,RAM:4,Fan:1,Mr:57"});    
        for(String str : result)
        {
        	System.out.println(str);
        }
	}

}
