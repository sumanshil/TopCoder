package com.topcoder.problems.Invitational2001;
//http://community.topcoder.com/stat?c=problem_statement&pm=164&rd=50
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Prerequisites
{
	class ClassName 
	{
		public ClassName(String name, Set<String> dependencies) 
		{
			this.name = name;
			this.prerequisites = dependencies;
		}
		public ClassName(String name) 
		{
			this.name = name;
		}
		String name;
		Set<String> prerequisites = new HashSet<String>();
		public int hashCode()
		{
			return name.hashCode();
		}
	}
	
	static class DeptName implements Comparable<DeptName>
	{
		String name;
		String stringPart;
		int intPart;
		
		public DeptName(String str)
		{
			this.name = str;
			populate(str);
		}
		
		private void populate(String str) 
		{
			char c = str.charAt(3);
			if (c >='0' && c <= '9')
			{
				this.stringPart = str.substring(0, 3);
				this.intPart = Integer.parseInt(str.substring(3));
			} 
			else
			{
				this.stringPart = str.substring(0, 4);
				this.intPart = Integer.parseInt(str.substring(4));
			}
			
		}

		public int compareTo(DeptName otherObject) 
		{
			if (otherObject.intPart < this.intPart)
			{
				return 1;
			}
			else if (otherObject.intPart > this.intPart)
			{
				return -1;
			}
			else 
			{
				if (otherObject.stringPart.compareTo(this.stringPart) < 0)
				{
					return 1;
				}
				else if (otherObject.stringPart.compareTo(this.stringPart) > 0)
				{
					return -1;
				}
			}
			return 0;
		}
		
	}
	public String[] orderClasses(String[] classSchedule)
	{
		Set<ClassName> classes = new CopyOnWriteArraySet<ClassName>();//new HashSet<ClassName>();
		
		for(String str : classSchedule)
		{
			String[] arr = str.split(":");
			String name = arr[0];
			if (arr.length == 1)
			{
				classes.add(new ClassName(name));
				continue;
			}
			String list = arr[1];
			String[] arr1 = list.split(" ");
			Set<String> dependencies = new HashSet<String>();
			for(String s1 : arr1)
			{
				if (s1.length()>0)
				{
					dependencies.add(s1);
				}
			}
			classes.add(new ClassName(name, dependencies));
		}
		
		Set<DeptName> classesToBeOrdered = new TreeSet<DeptName>();
		Set<DeptName> classesToBeOrderedOld = new TreeSet<DeptName>();
		Set<String> result = new LinkedHashSet<String>();
		for(ClassName className : classes)
		{
			if (className.prerequisites.size() == 0)
			{
				classesToBeOrdered.add(new DeptName(className.name));
				classes.remove(className);
			}
		}

		while(true)
		{		
			if (classes.isEmpty())
			{
				break;
			}
			
			DeptName classToBeCompleted = null;
			if (classesToBeOrdered.size() > 0)
			{
				classToBeCompleted = classesToBeOrdered.iterator().next();
				classesToBeOrdered.remove(classToBeCompleted);
			} 
			else 
			{
				break;
			}
			for(ClassName classN : classes)
			{
				if ( classN.prerequisites.contains(classToBeCompleted.name) )
				{
					classN.prerequisites.remove(classToBeCompleted.name);
				}
			}	

			for(ClassName className : classes)
			{
				if ( className.prerequisites.size() == 0 )
				{
					classesToBeOrdered.add(new DeptName(className.name));
					classes.remove(className);
				}
			}

			if (classesToBeOrdered.size() == classesToBeOrderedOld.size())
			{
				if (classesToBeOrdered.containsAll(classesToBeOrderedOld))
				{
					break;
				}
			}

			if (classesToBeOrdered.size() > 0)
			{ 
				result.add(classToBeCompleted.name);
			}
			classesToBeOrderedOld = new TreeSet<DeptName>(classesToBeOrdered);
			//classes = copyClasses;
		}
		if (classesToBeOrdered.size() > 0)
		{
			for(DeptName dept : classesToBeOrdered)
			{
				result.add(dept.name);
			}
		}
		return (String[])result.toArray(new String[0]);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String[] arr = {
//				"CSE121: CSE110",
//				"CSE110:",
//				"MATH122:",
//				};
//		String[] arr = {
//				"ENGL111: ENGL110",
//				"ENGL110: ENGL111"
//				};
//		String[] arr = {
//				"ENGL111: ENGL110"
//		};
//		String[] arr ={
//					"CSE258: CSE244 CSE243 INTR100",
//					"CSE221: CSE254 INTR100",
//					"CSE254: CSE111 MATH210 INTR100",
//					"CSE244: CSE243 MATH210 INTR100",
//					"MATH210: INTR100",
//					"CSE101: INTR100",
//					"CSE111: INTR100",
//					"ECE201: CSE111 INTR100",
//					"ECE111: INTR100",
//					"CSE243: CSE254",
//					"INTR100:",
//					};				
//		String[] arr = 	{"BUBB100:", "DUB101:", "ZUB100:", "ZUBB140:", "ZUBA150:", "JUM104:"};
//		String[] arr = 	{"MAT122:", "CSC121: CSC110", "CSC110:"};
//		String[] arr = 	{"CSC311: CSC211", "CSC211: CSC111", "CSC111: CSC311"};
		String[] arr = {"CSE258: CSE244 CSE243 INTR100", "CSE221: CSE254 INTR100", "CSE254: CSE111 MATH210 INTR100", "CSE244: CSE243 MATH210 INTR100", "MATH210: INTR100", "CSE101: INTR100", "CSE111: INTR100", "ECE201: CSE111 INTR100", "ECE111: INTR100", "CSE243: CSE254", "INTR100:"};
        String[] result = new Prerequisites().orderClasses(arr);
        for(String str : result)
        {
        	System.out.println(str);
        }
//         DeptName dept = new DeptName("BUBB100");
//         System.out.println(dept.stringPart);
//         System.out.println(dept.intPart);
//         
//         dept = new DeptName("ZUBA150");
//         System.out.println(dept.stringPart);
//         System.out.println(dept.intPart);
	}
	

}
