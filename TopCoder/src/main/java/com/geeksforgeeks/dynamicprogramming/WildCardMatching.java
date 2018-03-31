package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/24/16.
 */
// https://www.youtube.com/watch?v=3ZDZ-N0EPV0
public class WildCardMatching {

    public void match(String input, String regex){
        boolean result = recursive(input, regex, input.length()-1, regex.length()-1);
        System.out.println(result);
    }

    private boolean recursive(String input, String regex, int inputIndex, int regexIndex) {
      if (inputIndex < 0 && regexIndex < 0){
          return true;
      }

     /* if ((inputIndex < 0 && regexIndex >= 0) || (regexIndex < 0 && inputIndex  >= 0)){
          return false;
      }*/

      if ( (inputIndex >= 0 && regexIndex >= 0) && (regex.charAt(regexIndex) == input.charAt(inputIndex)) ) {
         // boolean result1 = recursive(input, regex, inputIndex-1, regexIndex);
          boolean result2 = recursive(input, regex, inputIndex-1, regexIndex-1 );
         // return result1 || result2;
          return result2;
      } else if ( (inputIndex >= 0 && regexIndex >= 0) && (regex.charAt(regexIndex) == '*')) {
          boolean result1 = recursive(input, regex, inputIndex-1, regexIndex);
          //boolean result2 = recursive(input, regex, inputIndex-1, regexIndex-1);
          boolean result3 = recursive(input, regex, inputIndex, regexIndex-1);
          return result1 ||  result3;
      } else if ((inputIndex >= 0 && regexIndex >= 0) && (regex.charAt(regexIndex) == '?')){
          boolean result1 = recursive(input, regex, inputIndex-1, regexIndex-1);
          return result1;
      }
      else {
          return false;
      }

    }


    public void dp(String input, String regex){
        int rowLength = input.length()+1;
        int colLength = regex.length()+1;
        boolean[][] matrix = new boolean[rowLength][colLength];
        matrix[0][0] = true;
        for ( int i = 1 ; i < colLength ; i++) {
            matrix[0][i] = false;
        }

        for ( int i = 1; i < rowLength ; i++) {
            matrix[i][0] = false;
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                if ((input.charAt(i-1) == regex.charAt(j-1)) || regex.charAt(j-1) == '?') {
                    matrix[i][j] = matrix[i-1][j-1];
                } else if ( regex.charAt(j-1) == '*'){
                    matrix[i][j] = matrix[i-1][j] || matrix[i][j-1] ;
                } else {
                    matrix[i][j] = false;
                }
            }
        }
        System.out.println(matrix[rowLength-1][colLength-1]);
    }

    public static void main(String[] args) {
        String input = "baaabab";
        String regex = "a*ab";
        new WildCardMatching().dp(input, regex);
    }
}
