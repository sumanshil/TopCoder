package com.geeksforgeeks.backtracking;

////http://www.geeksforgeeks.org/check-given-string-sum-string/

import java.util.Stack;

public class CheckIfAStringIsSumStringIterative {

    class StackNode {
        String str;
        int index;
        int length;

        StackNode(String str, int index, int length) {
            this.str = str;
            this.index = index;
            this.length = length;
        }

    }
    private Stack<StackNode> stack = new Stack<>();
    public void find (String str) {
        String s = str.charAt(0)+"";
        stack.push(new StackNode(s, 0, 1));
        int startIndex = 1;
        int length = 1;
        boolean found = false;
        while (true){
            int i = startIndex;
            for (  ; i + length < str.length() ;  ) {
                String str1 = str.substring(i, (i+length));
                StackNode topNode = stack.peek();
                Integer number1 = Integer.parseInt(str1);
                Integer number2 = Integer.parseInt(topNode.str);
                int number3 = number1 + number2;
                String str3 = Integer.toString(number3);
                if ( i + length + str3.length() <= str.length()){
                    String nextString = str.substring(i+length, i + length + str3.length());
                    if (nextString.equals(str3)) {
                        found = true;
                        if ( (i+ length + str3.length()) == str.length()){
                            System.out.println("Found solution");
                            return;
                        } else {
                            stack.push(new StackNode(str.substring(i, i+ length), i, length ));
                            startIndex = i + length;
                            i = startIndex;
                            length = 1;
                            continue;
                        }
                    }
                }
                length++;
            }
            if (!found){
                if (stack.isEmpty()){
                    break;
                }
                StackNode stackNode = stack.pop();
                startIndex = stackNode.index;
                length = stackNode.length + 1;
                if (length < str.length()) {
                    if (stack.isEmpty()) {
                        stack.push(new StackNode(str.substring(startIndex, length), startIndex, length));
                        startIndex = startIndex + length;
                        length = 1;
                    }
                }
            } else {
                found = false;
            }
        }
    }

    public static void main(String[] args) {
        String str = "2368";
        new CheckIfAStringIsSumStringIterative().find(str);
    }
}
