package com.topcoder.problems.round14;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Inheritance {
    class  Class{
        String name;
        Class parent;
        Set<String> methods;
        public Class(String name, Class parent, Set<String> methods){
            this.name = name;
            this.parent = parent;
            this.methods = methods;           
        }
    }
     public String resolve(String[] classes, String className, String methodName){
           Map<String, Class> map = new HashMap<String, Class>();
           for(String classString : classes){
               String[] str = classString.split("\\|");
               String classNameStr = str[0];
               String parentNameStr = str[1];
               Set<String> set = new HashSet<String>();
               if (str.length > 2){
                   String methods = str[2];
                   String[] methodsArr = methods.split(",");                   
                   for(String method : methodsArr){
                       set.add(method);
                   }
               }
               Class parent = map.get(parentNameStr);
               map.put(classNameStr, new Class(classNameStr, parent, set));
           }
           Class resolvedClass = resolveUtil(map, className, methodName);
           if (resolvedClass != null)
               return resolvedClass.name;
           else 
               return "";
     }
    private Class resolveUtil(Map<String, Class> map, String className, String methodName) {
        if (map.get(className).methods.contains(methodName)){
            return map.get(className);
        }
        if ( map.get(className).parent != null){
            return resolveUtil(map, map.get(className).parent.name, methodName);
        } else {
            return null;
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String result = new Inheritance().resolve(new String[]{"Object||toString,getClass", "String|Object|toString", "InputStream|Object|read,close,reset", "FileInputStream|InputStream|read,close"}, "FileInputStream", "toString");
        System.out.println(result);

    }

}
