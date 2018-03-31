package com.geeksforgeeks.backtracking;

import java.util.Stack;
import java.util.stream.Collectors;

//http://www.geeksforgeeks.org/combinational-sum/
public class CombinationalSumIterative {

    private Stack<Integer> stack = new Stack<>();

    public void find(int arr[], int sum) {
        int currentSum = sum;
        for ( int i = 0 ; i < arr.length ; i++){
            if (arr[i] <= sum) {
                stack.push(i);
                currentSum = currentSum - arr[i];
                iterative(arr, currentSum, sum);
                stack = new Stack<>();
                currentSum = sum;
            }
        }
    }

    private void iterative(int[] arr, int currentSum, int sum) {
        int index = -1;
        while (true) {
            if (stack.isEmpty()){
                break;
            }
            int s = stack.stream().mapToInt(e -> arr[e]).sum();
            if (s == sum){
                String result = stack.stream().map(e -> arr[e]+"").collect(Collectors.joining("=>"));
                System.out.println(result);
                int in = stack.pop();
                index = in + 1;
                currentSum += arr[in];
            }

            if (index == -1){
                int in = stack.peek();
                if (arr[in] <= currentSum ){
                    index = in;
                } else {
                    index = in + 1;
                }
            } else {
                if (index == arr.length) {
                    s = stack.stream().mapToInt(e -> arr[e]).sum();
                    if (s == sum){
                        String result = stack.stream().map(e -> arr[e]+"").collect(Collectors.joining("=>"));
                        System.out.println(result);
                    }
                    if (stack.size() > 0) {
                        int in = stack.pop();
                        currentSum += arr[in];
                        index = in + 1;
                    }
                } else {
                    if (arr[index] <= currentSum){
                        stack.push(index);
                        currentSum -= arr[index];
                    } else {
                        index += 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {2, 4, 6, 8};
        int sum = 8;
        new CombinationalSumIterative().find(arr, sum);
    }
}
