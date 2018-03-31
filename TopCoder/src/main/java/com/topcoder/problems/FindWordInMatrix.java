package com.topcoder.problems;

import java.util.ArrayList;
import java.util.List;

public class FindWordInMatrix {

    char c[][] = {{'s','u','m','x','y'},
                  {'p','a','q','a','i'},
                  {'n','l','p','a','s'},
                  {'s','d','o','o','g'},
                  {'s','u','l','a','n'}
                 };
    boolean[][] visited = new boolean[5][5];
    List<Character> list = new ArrayList<Character>(); 
    public boolean isWordPresent(String word, int x, int y){
        boolean wordFound = false;
        boolean canProceed = true;
        visited[x][y] = true;
        list.add(c[x][y]);
        String str = getContentAsString(list);
        if (word.equals(str)){
            wordFound = true;
        } else if (str.length() > word.length()){
            canProceed =  false;
        }
        
        if (canProceed &&!wordFound){
            // go right
            if (isValidPosition(x,y+1)){
                wordFound = isWordPresent(word, x, y+1);
            }
            if (canProceed && !wordFound){
                // go left
                if (isValidPosition(x,y-1)){
                    wordFound = isWordPresent(word, x, y-1);
                }
            }
            if (canProceed && !wordFound){
                // go down
                if (isValidPosition(x+1,y)){
                    wordFound = isWordPresent(word, x+1, y);
                }
            }
            if (canProceed && !wordFound){
                if (isValidPosition(x+1,y-1)){
                    wordFound = isWordPresent(word, x+1, y-1);
                }                
            }
            if (canProceed && !wordFound){
                if (isValidPosition(x+1,y+1)){
                    wordFound = isWordPresent(word, x+1, y+1);
                }                
            }
            
        }
        list.remove(list.size()-1);
        visited[x][y] = false;
        return wordFound;
    }
    private String getContentAsString(List<Character> list) {
        StringBuffer sb = new StringBuffer();
        for(Character str : list){
            sb.append(str);
        }
        return sb.toString();
    }
    private boolean isValidPosition(int x, int y) {
        return (x >=0 && x <= 4) &&(y >=0 && y <=4) && !visited[x][y];        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        boolean result = new FindWordInMatrix().isWordPresent("sumas", 0, 0);
        System.out.println(result);

    }

}
