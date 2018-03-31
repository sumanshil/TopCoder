package com.geeksforgeeks.backtracking;
//http://www.geeksforgeeks.org/tug-of-war/
public class TugOfWar {

  //  public static int[] arr= {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
    public static int[] arr= {23, 45, -34, 12, 0, 98};
    public static boolean[] curr_elements = new boolean[arr.length];
    public static boolean[] soln = new boolean[arr.length];
    public static int minDiff = Integer.MAX_VALUE;
    public static int n = arr.length;
    public static void TOWUtil(int no_of_selected_elements, int sum, int curr_sum, int curr_position){
        //  checks whether the it is going out of bound
        if (curr_position == n){
            return;
        }
        
        // checks that the numbers of elements left are not less than the
        // number of elements required to form the solution   
        if ((n/2 -no_of_selected_elements)> (n-curr_position)){
            return;
        }
        
        // consider the cases when current element is not included in the solution
        TOWUtil(no_of_selected_elements, sum, curr_sum, curr_position+1);
        // add the current element to the solution
        no_of_selected_elements++;
        curr_sum = curr_sum+arr[curr_position];
        curr_elements[curr_position] = true;
   
        //  checks if a solution is formed
        if (no_of_selected_elements == n/2){
        
            // checks if the solution formed is better than the best solution so far
            if (Math.abs(sum/2 -curr_sum)< minDiff){
                minDiff = Math.abs(sum/2 -curr_sum);
                for(int i = 0 ; i < n ; i++){
                    soln[i] = curr_elements[i];
                }
            }
        } else {
            // consider the cases where current element is included in the solution
            TOWUtil(no_of_selected_elements, sum, curr_sum, curr_position+1);            
        }
        //  removes current element before returning to the caller of this function
        curr_elements[curr_position] = false;
    }
    
    public static void tugOfWar(int[] arr){
        int sum = 0;
        for(int i = 0 ; i < arr.length ; i++){
            sum += arr[i];
            curr_elements[i] = soln[i] = false;            
        }
        TOWUtil(0, sum, 0, 0);
        
        System.out.println("First Set");
        for(int i = 0 ; i < n ; i++){
            if (soln[i]){
                System.out.println(arr[i]);
            }
        }
        
        System.out.println("Second Set");
        for(int i = 0 ; i < n ; i++){
            if (!soln[i]){
                System.out.println(arr[i]);
            }
        }
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        tugOfWar(arr);
    }

}
