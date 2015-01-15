package com.topcoder.problems;

import java.util.ArrayList;

//http://community.topcoder.com/stat?c=problem_statement&pm=75&rd=3004
public class Partitions {

//    public int currLevel =1 ;
//    public int[] arr = null;
//    public void getKthPartition(int n, int k){
//        int[] arr = new int[1];
//        if(k == 1)
//            return;
//        arr[0] = n;
//        getKthPartitionUtil(arr, 0, k);
//    }
//    
//    public void getKthPartitionUtil(int[] arr, int index, int k){
//        int element  = arr[index];
//        int nextIndex = 0;
//        int[] arr1 = null;
//        if (element > 1){
//            currLevel = currLevel+1;
//            element = element-1;
//            boolean isSameSize = false;
//            if (index+1>=arr.length || (index+1 <arr.length && arr[index+1]==1&& ((arr[index]-arr[index+1]) == 1))){
//                arr1 = new int[arr.length+1];
//                arr1[index+1] = 1;
//                isSameSize = false;
//            } else {
//                arr1 = new int[arr.length];
//                arr1[index+1] = arr[index+1]+1;
//                isSameSize = true;                
//            }
//            
//            arr1[index] = element;                
//            for(int i=0;i<index ;i++ ){
//                arr1[i]= arr[i];
//            }
//            if (index+1 < arr.length){
//                if (!isSameSize){
//                    for(int i = index+1; i <arr.length ; i++){
//                        arr1[i+1] = arr[i];
//                    }
//                } else {
//                    if (index+2 < arr.length){
//                        for(int i = index+2; i <arr.length ; i++){
//                            arr1[i] = arr[i];
//                        }                    
//                    }
//                }
//            }
//                
////            } else {
////                arr1 = new int[arr.length];
////                arr1[index] = element;
////                arr1[index+1] = arr[index+1]+1;
////                
////                for(int i=0;i<index ;i++ ){
////                    arr1[i]= arr[i];
////                }
////                for(int i = index+1; i <arr.length ; i++){
////                    arr1[i] = arr[i];
////                }
////            }           
//            //if (currLevel == k) {
//                System.out.println("Current Level "+currLevel);
//                for(int i = 0 ; i < arr1.length ; i++){
//                    System.out.print(arr1[i]+" ");
//                }
//                System.out.println();
//            //}
//            this.arr = arr1;
//            getKthPartitionUtil(this.arr, index+1, k);
//            getKthPartitionUtil(this.arr, index, k);                    
//        }
//    }
//    
    int nparts = 0;
    ArrayList res = new ArrayList();
    int part[];
    public ArrayList getKthPartition( int n, int k )
    {
       part = new int[ n ];
       gen( n, n, 0, k );
       return res;   
    }
    void gen( int n, int max, int idx, int k )
    {
     //  System.out.println( "gen(" + n + "," + max + "," + idx + "," + k + ")" );
       if (n < 0) return;
       if (n == 0) {
          nparts++;
          System.out.println("Number " + nparts);
          for(int i = 0 ; i < idx ; i++){
              System.out.print(part[i]+" ");
          }
          System.out.println();
          if (nparts == k) {
             for (int i = 0; i < idx; i++)
                res.add( new Integer( part[ i ] ) );
          }
          return;
       }
       for (int i = max; i > 0; i--) {
          part[ idx ] = i;
          gen( n - i, i, idx + 1, k );
          System.out.println("Returning idx "+idx+" i "+i);
       }
    }    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        new Partitions().getKthPartition(5, 4);

    }

}
