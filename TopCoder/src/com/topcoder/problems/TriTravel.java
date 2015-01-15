package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=68&rd=2008
public class TriTravel {
//{5, 4, 1, 2, 8, -1}
    public int maxSum= Integer.MIN_VALUE;
    public int tempSum = 0;
    
    public int[] getChildren(int index, int level, int[] param){
        int[] children = new int[2];
        if (((index+level) < param.length) &&((index + level + 1)< param.length)){
            children[0] = index+level;
            children[1] = index+level+1;
            return children;
        } else {
            return null;
        }
        
    }
    
//    public void getSum(int value, int level, int[] param){
//        tempSum +=value;
//        int[] children = getChildren(level, param);
//        if (children != null){
//            for (int c : children){
//               getSum(c, level+1, param); 
//            }
//        }
//        if (tempSum > maxSum){
//            maxSum = tempSum;
//        }
//        tempSum -= value;
//    }

    public void getSum(int index, int level, int[] param){
        tempSum +=param[index];
        int[] childrenIndex = getChildren(index, level, param);
        if (childrenIndex != null){
            for (int c : childrenIndex){
               getSum(c, level+1, param); 
            }
        }
        if (childrenIndex == null && tempSum > maxSum ){
            maxSum = tempSum;
        }
        tempSum -= param[index];
    }
    
    public int getSumDynamic(int[] param){
        int[][] values = new int[10][10];
        int[][] worth = new int[10][10];
        
        int level = 1;
        
        int i = 0;
        while(i < param.length){
            int k = i;
            for( int j = 0; j < level ; j++){
                values[level][j] = param[k];
                k++;
            }
            i = k;
            level++;
        }
        
        for(int j = level -1 ; j >0 ;j--){            
            for(int k = 0 ; k < j ; k++){
                if (j == level -1){
                    worth[j][k] = values[j][k];
                } else {
                    worth[j][k] = values[j][k] + Math.max(worth[j+1][k], worth[j+1][k+1]);
                }
            }
        }
        
        return worth[1][0];
    }

    public int bestWayDown(int[] param){
        //getSum(0, 1, param);
        int result = getSumDynamic(param);
//        for(int i = 0 ; i < param.length/2-1 ; i++){
//            int level = i+1;
//            int number = param[i];
//            
//            //int k = i;
////            for(int j = 0 ; j < level; j++){
////                System.out.println("Parent value "+ param[k]);
////                System.out.println("Childen 1 "+param[k+level]);
////                System.out.println("Childen 2 "+param[k+level+1]);
////                k++;
////            }
//            int[] children = getChildren(level, param);
//            for(int k : children){
//                
//            }
//        }
        return result;
    }
//    3 + 3 + 1
//            3
//          9   0
//        9   0   0
//     9    0   0   0
//   9    0   0   0    100
//                        5
//                      9   2
//                   15   12   0
//                13    16   0    0
//             21    17   0    0    0
//          -99   -99   -99  -99  -99  0   
                   
    /**
     * @param args
     */
    public static void main(String[] args) {
       // int[] arr = {5, 4, 1, 2, 8, -1};
       //int[] arr =     {6, 1, 1, 1, 1, 1, 1, 2, 1, 1}  ;
        //int[] arr = {-10};
        //int[] arr = {3, 9, 0, 9, 0, 0, 9, 0, 0, 0, 9, 0, 0, 0, 100};
        //int[] arr = {5, 9, 2, 15, 12, 0, 13, 16, 0, 0, 21, 17, 0, 0, 0, -99, -99, -99, -99, -99, 0};
        int[] arr =     {7, 8, 5, 4, 3, 2, 7, 6, 7, 8, 1, 3, 9, 6, 2};
        
        int result = new TriTravel().bestWayDown(arr);
        System.out.println(result);
    }

}
