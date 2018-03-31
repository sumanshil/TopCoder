package com.geeksforgeeks.array;

//https://www.geeksforgeeks.org/find-largest-d-in-array-such-that-a-b-c-d/
public class FindLargestDInArray {

    public void find (int arr[]) {
        for ( int d = arr.length-1 ; d > 2 ; d--) {
            int lastElementIndex = d - 1;
            for ( int i = 0 ; i < d - 2; i++ ) {
                if (arr[i] * 3 > arr[d]) {
                    System.out.println("Impossible");
                    return;
                }
                for ( int j = i + 1 ; j < lastElementIndex ;) {
                    if (arr[i] + 2* arr[j] > arr[d]) {
                        System.out.println("Impossible");
                        return;
                    }
                    int sum = arr[i] + arr[j] + arr[lastElementIndex];
                    if (sum == arr[d]) {
                        System.out.println(arr[d]);
                        return;
                    } else if (sum > arr[d]) {
                        lastElementIndex--;
                    } else {
                        j++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {2, 16, 64, 256, 1024};
        new FindLargestDInArray().find(arr);
    }
}
