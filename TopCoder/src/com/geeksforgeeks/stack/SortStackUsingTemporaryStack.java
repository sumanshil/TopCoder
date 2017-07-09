package com.geeksforgeeks.stack;

import java.util.Stack;

/**
 * Created by sshil on 7/3/17.
 */
//http://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
public class SortStackUsingTemporaryStack {

    public void sort(Stack<Integer> stack){
        Stack<Integer> tempStack = new Stack<>();
        while (!stack.isEmpty()){
            Integer current = stack.pop();
            insertInTemporaryStack(stack, current, tempStack);
        }
        while (!tempStack.isEmpty()){
            System.out.println(tempStack.pop());
        }
    }

    private void insertInTemporaryStack(Stack<Integer> original,
                                        Integer current,
                                        Stack<Integer> tempStack) {
        int count = 0;
        if (tempStack.isEmpty()){
            tempStack.push(current);
            return;
        }
        while (!tempStack.isEmpty() && tempStack.peek() < current){
            original.push(tempStack.pop());
            count++;
        }
        tempStack.push(current);
        for ( int i = 0 ; i  < count ; i++ ) {
            tempStack.push(original.pop());
        }
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(23);
        stack.push(92);
        stack.push(98);
        stack.push(31);
        stack.push(3);
        stack.push(34);
        new SortStackUsingTemporaryStack().sort(stack);


    }
}
