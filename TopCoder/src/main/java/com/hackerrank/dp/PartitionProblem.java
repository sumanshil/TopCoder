package com.hackerrank.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sshil on 8/16/2016.
 */
public class PartitionProblem {

    public int find(int[] arr){
        int sum = 0;
        for ( int i = 0 ; i < arr.length ; i++){
            sum += arr[i];
        }
        if (sum % 2 != 0){
            return 0;
        }

        int halfSum = sum/2;

//        boolean isPartitionAvailable = isPartitionAvailable(arr, arr.length-1, halfSum);
//        System.out.println(isPartitionAvailable);
        Optional<Partitions> partition = dp(arr);
        if (partition.isPresent()){
            List<Integer> left = partition.get().left;
            List<Integer> right = partition.get().right;
            left.stream().forEach(System.out::println);
            right.stream().forEach(System.out::println);
        }
        return -1;
    }

    private boolean isPartitionAvailable(int[] arr, int index, int sum) {
        if (sum == 0){
            return true;
        }
        if (index < 0){
            return false;
        }
        return isPartitionAvailable(arr, index-1, sum)
            || isPartitionAvailable(arr, index-1, sum-arr[index]);
    }

    class Partitions{
        private List<Integer> left;
        private List<Integer> right;

        public Partitions(List<Integer> left, List<Integer> right){
            this.left = left;
            this.right = right;
        }

    }

    public int countPartitions(int[] arr){
        Optional<Partitions> partitions = dp(arr);
        if (!partitions.isPresent()){
            return 0;
        }
        int result = recursive(partitions);
        return result;
    }

    private int recursive(Optional<Partitions> partitions) {
        if (!partitions.isPresent()){
            return 0;
        }
        List<Integer> left = partitions.get().left;
        List<Integer> right = partitions.get().right;
        int[] leftArr = new int[left.size()];
        int[] rightArr = new int[right.size()];
        for (int i = 0 ; i < left.size() ; i++){
            leftArr[i] = left.get(i);
        }
        for (int i = 0 ; i < right.size() ; i++){
            rightArr[i] = right.get(i);
        }

        Optional<Partitions> leftPartition = dp(leftArr);
        Optional<Partitions> rightPartition = dp(rightArr);
        int leftResult = recursive(leftPartition);
        int rightResult = recursive(rightPartition);
        return 1 + Math.max(leftResult, rightResult);
    }

    private Optional<Partitions> dp(int[] arr){
        if (arr.length == 1){
            return Optional.empty();
        }

        int sum = Arrays.stream(arr).sum();
        if (sum % 2!= 0){
         //   System.out.println("Array can not be divided into two partiions");
            return Optional.empty();
        }
        int halfSum = sum / 2;
        boolean[][] matrix = new boolean[halfSum+1][arr.length+1];
        int rowlength = matrix.length;
        int colLength = matrix[0].length;
        for (int i = 0 ; i < colLength ; i++){
            matrix[0][i] = true;
        }
        for ( int i = 1 ; i < rowlength; i++){
            matrix[i][0] = false;
        }

        for ( int i = 1 ; i < rowlength ; i++) {
            for ( int j =1 ; j < colLength ; j++) {
                matrix[i][j] = matrix[i][j-1] ;
                if (arr[j-1] <= i){
                    matrix[i][j] = matrix[i][j] || matrix[i-arr[j-1]][j-1];
                }
            }
        }
        System.out.println(matrix[rowlength-1][colLength-1]);
        List<Integer> indexList = new ArrayList<>();
        int currentCol = colLength-1;
        int currentRow = rowlength-1;

        indexList.add(currentCol-1);
        currentRow = currentRow - arr[currentCol-1];
        if (matrix[rowlength-1][colLength-1]){
            while(true){
                if (currentRow <= 0){
                    break;
                }
                for ( int i =1 ; i < colLength ; i++) {
                   if (matrix[currentRow][i] && ! matrix[currentRow][i-1]){
                       indexList.add(i-1);
                       currentRow = currentRow - arr[i-1];
                   }
                }
            }
        }

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(arr).forEach(e -> {
            if (indexList.contains(index.get())){
                leftList.add(arr[index.get()]);
            } else {
                rightList.add(arr[index.get()]);
            }
            index.incrementAndGet();
        });
        return Optional.of(new Partitions(leftList, rightList));
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 0, 1, 1, 0, 1};
        int result = new PartitionProblem().countPartitions(arr);
        System.out.println(result);
    }
}
