package com.leetcode;

import java.util.Stack;

//https://leetcode.com/problems/simplify-path/
public class SimplifyPath {

    public String simplifyPath(String path) {
        Stack<String> directoryStack = new Stack<>();

        String[] arr = path.split("/");

        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i].equals("..")) {
                if (!directoryStack.isEmpty()) {
                    directoryStack.pop();
                }
            } else if (arr[i].trim().length() > 0 && !arr[i].equals(".")) {
                directoryStack.push(arr[i].trim());
            }
        }

        if (directoryStack.isEmpty()) {
            return "/";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            while (!directoryStack.isEmpty()) {
                stringBuilder.insert(0, "/"+directoryStack.pop());
            }
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        //String str = "/home/";
        //String str = "/../";
        //String str = "/home//foo/";
        //String str = "/a/./b/../../c/";
        //String str = "/a/../../b/../c//.//";
        String str = "/a//b////c/d//././/..";
        String result = new SimplifyPath().simplifyPath(str);
        System.out.println(result);
    }
}
