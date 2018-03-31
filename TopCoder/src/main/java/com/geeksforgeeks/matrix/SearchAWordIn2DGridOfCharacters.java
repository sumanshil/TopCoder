package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 11/24/2015.
 */
public class SearchAWordIn2DGridOfCharacters {

    char[][] grid = {
        {'G','E','E','K','S','F','O','R','G','E','E','K','S'},
        {'G','E','E','K','S','Q','U','I','Z','G','E','E','K'},
        {'I','D','E','Q','A','P','R','A','C','T','I','C','E'}
    };

    public void find(String word){
        for (int i = 0 ; i < grid.length ; i++) {
            for ( int j = 0 ; j < grid[0].length ; j++) {
                if (grid[i][j] != word.charAt(0)){
                    continue;
                }
                for (int k = -1; k<= 1; k++) {
                   for (int l = -1 ; l <= 1 ; l++) {
                       if ( k ==0 && l ==0){
                           continue;
                       }
                       int posX = i;
                       int posY = j;

                       int length = word.length();
                       StringBuffer sb = new StringBuffer();
                       while (length > 0 ){
                           sb.append(grid[posX][posY]);
                           posX = posX + k;
                           posY = posY + l;
                           if (!valid(posX, posY)
                               || ! word.substring(0, sb.length()).intern()
                                      .equals(sb.toString().intern())){
                               break;
                           }
                           length--;
                       }
                       if (sb.toString().intern().equals(word.intern())){
                           System.out.println("X= "+i +" Y= "+j);
                       }
                   }
                }
            }
        }
    }

    private boolean valid(int posX, int posY) {
        return posX >= 0 &&
               posX < grid.length &&
               posY >= 0 &&
               posY < grid[0].length;

    }

    public static void main(String[] args){
        new SearchAWordIn2DGridOfCharacters().find("GEEKS");
    }
}
