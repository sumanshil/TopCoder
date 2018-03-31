package com.topcoder.problems.round13;
//http://community.topcoder.com/stat?c=problem_statement&pm=102&rd=3012
public class WordFinder {
    private char[][] puzzlematrix = null;
    private boolean[][] visited = null;
                      
    public String findWords(String[] puzzle, String[] words){
        char[][] puzzlematrix = new char[puzzle.length][puzzle[0].length()];
        int index = 0 ;
        for(String str : puzzle){
            for(int i = 0 ; i < str.length(); i++){
                puzzlematrix[index][i] =str.charAt(i);
            }
            index++;
        }
        this.puzzlematrix = puzzlematrix;
        StringBuffer sb = new StringBuffer();
        for(String word : words){
            boolean wordFound = false;
            for(int i = 0 ; i < puzzle.length ; i++){
                for(int j = 0 ; j < puzzle[0].length() ; j++){
                    if (this.puzzlematrix[i][j] == word.charAt(0)){
                        if (wordExist(i,j,word,puzzlematrix.length,puzzlematrix[0].length)){
                            wordFound = true;
                            sb.append("{"+(i+1)+','+(j+1)+"},");
                        }
                    }
                    if (wordFound)
                        break;
                }
                if (wordFound)
                    break;
            }
            if (!wordFound){
                sb.append(",Not found,");
            }
        }
        return sb.toString();
    }
    private boolean wordExist(int i, int j, String word, int lengthx, int lengthy) {
        int startX = i;
        int startY = j;
        int count = 0;
        StringBuffer sb = new StringBuffer();
        //try right
        while(isValid(startX, startY, lengthx, lengthy) && count < word.length()){
            sb.append(this.puzzlematrix[startX][startY++]);    
            count++;
        }
        if (sb.toString().length() == word.length()&& sb.toString().intern().equals(word)){
            return true;
        }
        //try left
        startX = i;
        startY = j;
        count = 0;
        sb = new StringBuffer();        
        while(isValid(startX, startY, lengthx, lengthy)&& count < word.length()){
            sb.append(this.puzzlematrix[startX][startY--]);
            count++;
        }
        if (sb.toString().length() == word.length()&& sb.toString().intern().equals(word)){
            return true;
        }
        
        //try downward
        startX = i;
        startY = j;
        count = 0;
        sb = new StringBuffer();        
        while(isValid(startX, startY, lengthx, lengthy)&& count < word.length()){
            sb.append(this.puzzlematrix[startX++][startY]);
            count++;
        }
        if (sb.toString().length() == word.length()&& sb.toString().intern().equals(word)){
            return true;
        }
        //try upward
        startX = i;
        startY = j;
        count = 0;
        sb = new StringBuffer();                
        while(isValid(startX, startY, lengthx, lengthy)&& count < word.length()){
            sb.append(this.puzzlematrix[startX--][startY]);    
            count++;
        }
        if (sb.toString().length() == word.length()&& sb.toString().intern().equals(word)){
            return true;
        }

        // try diagonal left upward
        startX = i;
        startY = j;
        count = 0;
        sb = new StringBuffer();                
        while(isValid(startX, startY, lengthx, lengthy)&& count < word.length()){
            sb.append(this.puzzlematrix[startX--][startY--]);
            count++;
        }
        if (sb.toString().length() == word.length()&& sb.toString().intern().equals(word)){
            return true;
        }

        // try diagonal right downward
        startX = i;
        startY = j;
        count = 0;
        sb = new StringBuffer();                
        while(isValid(startX, startY, lengthx, lengthy)&& count < word.length()){
            sb.append(this.puzzlematrix[startX++][startY++]);
            count++;
        }
        if (sb.toString().length() == word.length()&& sb.toString().intern().equals(word)){
            return true;
        }

        // try diagonal left downward
        startX = i;
        startY = j;
        count = 0;
        sb = new StringBuffer();                
        while(isValid(startX, startY, lengthx, lengthy)&& count < word.length()){
            sb.append(this.puzzlematrix[startX++][startY--]);
            count++;
        }
        if (sb.toString().length() == word.length()&& sb.toString().intern().equals(word)){
            return true;
        }

        // try diagonal right upward
        startX = i;
        startY = j;
        count = 0;
        sb = new StringBuffer();                
        while(isValid(startX, startY, lengthx, lengthy)&& count < word.length()){
            sb.append(this.puzzlematrix[startX--][startY++]);
            count++;
        }
        if (sb.toString().length() == word.length()&& sb.toString().intern().equals(word)){
            return true;
        }

        return false;
    }
    
    public boolean isValid (int x, int y, int lengthx, int lengthy){
        return ((x>=0 && x< lengthx) && (y>=0 && y < lengthy));
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] puzzle = {"ETAQAZRPNCLJ", "YKCURTAEOAAI", "EJEETCRNOCVT", "BCCECOAILCAS", "ACJCTCMTLCGI", "SPEEDRBOATCA", "AUTOMOAZBILE", "TRRUCKCIAIAU", "OTUANAAENALP", "AURPIHSTOMOE"};
        String[] word = {"AUTOMOBILE", "JET", "TRUCK", "BALLOON", "CAR", "VAN", "BOAT", "PLANE", "TRAIN", "SHIP", "CAB"};
        String result = new WordFinder().findWords(puzzle, word);
        System.out.println(result);
    }

}
