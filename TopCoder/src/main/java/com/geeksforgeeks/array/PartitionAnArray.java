package com.geeksforgeeks.array;

//http://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
public class PartitionAnArray {

    public void find (int arr[]){

        // partition

        int index1 = 0;
        int index2 = arr.length-1;

        while (index1 < index2){
            if (arr[index1] < 0 && arr[index2] > 0) {
                swap(arr, index1, index2);
                index1++;
                index2--;
            } else if (arr[index1] < 0 && arr[index2] < 0){
                index2--;
            } else {
                index1++;
            }
        }

        int pos = 1;
        int neg = index1;

        while (pos < arr.length && neg < arr.length && pos < neg) {
            if (arr[pos] > 0 && arr[neg] < 0){
                swap(arr, pos, neg);
                pos += 2;
                neg ++;
            } else {
                break;
            }
        }


    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {-1, 2, -3, 4, 5, 6, -7, 8, 9};
        new PartitionAnArray().find(arr);
    }

}
