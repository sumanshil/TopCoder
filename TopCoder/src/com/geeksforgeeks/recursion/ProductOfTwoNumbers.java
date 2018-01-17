package com.geeksforgeeks.recursion;

//https://www.geeksforgeeks.org/product-2-numbers-using-recursion/
public class ProductOfTwoNumbers {

    public void find (int number1, int number2) {
        int result = recursive(number1, number2);
        System.out.println(result);
    }

    private int recursive(int number1, int number2) {
        if (number2 >=1 && number2 <= 9) {
            return number1 * number2;
        }

        int remainder = number2 % 10;
        int remaining = number2 / 10;
        int next = recursive(number1, remaining);
        return  next*10 + remainder;
    }


    public static void main(String[] args) {
        int number1 = 100;
        int number2 = 5;
        new ProductOfTwoNumbers().find(number1, number2);
    }

}
