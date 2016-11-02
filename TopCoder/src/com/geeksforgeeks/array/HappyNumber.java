package com.geeksforgeeks.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sshil on 10/21/16.
 */
//http://www.geeksforgeeks.org/happy-number/
public class HappyNumber {

    public void find(int number){
        Set<Integer> set = new HashSet<>();
        set.add(number);
        while(!(set.contains(number) && set.size() > 1 )&& number != 1){
            set.add(number);
            number = getSquareRecursive(number);
        }
        if (number == 1){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    public void findWithPointer( int number ) {
        int slow, fast;
        slow = fast = number;
        do {
            slow = getSquareRecursive(slow);
            fast = getSquareRecursive(getSquareRecursive(fast));
        } while (slow != fast);
        System.out.println(slow ==1 ?true:false);
    }

    private int getSquareRecursive(int number) {
        int result = 0;
        while(true) {
            int x = number%10;
            int y = number/10;
            result += Math.pow(x, 2);
            if (y <= 9){
                result += Math.pow(y, 2);
                break;
            }
            number = y;
        }
        return result;
    }

    public static void main(String[] args) {
        int number = 13;
        new HappyNumber().findWithPointer(number);
    }
}
