package com.topcoder.problems.round173;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sshil on 7/9/17.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1958&rd=4670
public class TreasureHunt {

    static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    static  abstract class Movement {
        private String direction;
        private int place;
        public Movement(String direction, int place) {
            this.direction = direction;
            this.place = place;
        }

        protected int getPlace(){
            return this.place;
        }

        public abstract Position translate(int x, int y);
    }

    static class NorthMovement extends Movement {

        public NorthMovement(String direction, int place) {
            super(direction, place);
        }

        @Override
        public Position translate(int x, int y) {
            return new Position(x, y-this.getPlace());
        }
    }

    static class SouthMovement extends Movement {

        public SouthMovement(String direction, int place) {
            super(direction, place);
        }

        @Override
        public Position translate(int x, int y) {
            return new Position(x, y + getPlace());
        }
    }

    static class EastMovement extends Movement {

        public EastMovement(String direction, int place) {
            super(direction, place);
        }

        @Override
        public Position translate(int x, int y) {
            return new Position(x + getPlace(), y);
        }
    }

    static class WestMovement extends Movement {

        public WestMovement(String direction, int place) {
            super(direction, place);
        }

        @Override
        public Position translate(int x, int y) {
            return new Position(x - getPlace(), y);
        }
    }

    static class MovementFactory {

        public static Movement newInstance(String str){
            String[] arr = str.split("\\s+");
            Movement retVal = null;
            switch (arr[0]){
                case "N":
                    retVal = new NorthMovement(arr[0], Integer.parseInt(arr[1]));
                    break;
                case "W":
                    retVal = new WestMovement(arr[0], Integer.parseInt(arr[1]));
                    break;
                case "E":
                    retVal = new EastMovement(arr[0], Integer.parseInt(arr[1]));
                    break;
                case "S":
                    retVal = new SouthMovement(arr[0], Integer.parseInt(arr[1]));
                    break;
            }
            return retVal;
        }

    }
    private static Movement toMovement(String str){
        return MovementFactory.newInstance(str);
    }

    public int[] findTreasure(String[] island, String[] instructions) {

        List<Movement> movementList = new LinkedList<>();
        Arrays.stream(instructions).map(e -> toMovement(e)).forEach(movementList::add);

        int rowLength = island.length;
        int colLength = island[0].length();
        char[][] ocean = new char[rowLength][colLength];
        int xPosition = 0;
        int yPosition = 0;
        for ( int y = 0 ; y < rowLength ; y++ ) {
            for (int x = 0 ; x < colLength ; x++) {
                if (island[y].charAt(x) == '.'){
                    ocean[y][x] = '.';
                } else if (island[y].charAt(x) == 'O'){
                    ocean[y][x] = 'O';
                } else {
                    xPosition = x;
                    yPosition = y;
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        int resultX = 0;
        int resultY = 0;
        for ( int y = 0 ; y < rowLength ; y++) {
            for ( int x = 0 ; x < colLength ; x++) {
                if (ocean[y][x] == 'O' || ocean[y][x] == '\u0000') {
                    if (isBeach(y, x, ocean)) {
                        System.out.println("Beach x : "+x+" : y : "+y);
                        Position finalPosition = null;
                        boolean isValid = true;
                        int yTemp = y;
                        int xTemp = x;
                        for (Movement movement : movementList){
                            finalPosition = movement.translate(xTemp, yTemp);
                            System.out.println("Final pos X : "+finalPosition.x);
                            System.out.println("Final pos Y : "+finalPosition.y);

                            if (!isValid(finalPosition.y, finalPosition.x, rowLength, colLength)){
                                isValid = false;
                                break;
                            }
                            if (ocean[finalPosition.y][finalPosition.x] == '.'){
                                isValid = false;
                                break;
                            }
                            xTemp = finalPosition.x;
                            yTemp = finalPosition.y;
                            //((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))
                        }
                        if (isValid) {
                            System.out.println(finalPosition.x + ":" + finalPosition.y);
                            int result = (int)(Math.pow((xPosition - finalPosition.x), 2) + Math.pow((yPosition - finalPosition.y), 2));
                            System.out.println("result = " + result);
                            System.out.println("minDistance = " + minDistance);

                            if (result < minDistance) {
                                minDistance = result;
                                resultX = finalPosition.x;
                                resultY = finalPosition.y;
                            }
                        }
                    }
                }
            }
        }
        return new int[] {resultX, resultY};
    }

    private boolean isBeach(int x, int y, char[][] ocean) {
        if (isValid(x-1, y, ocean.length, ocean[0].length )
                && ocean[x-1][y] == '.'){
            return true;
        }
        if (isValid(x, y-1, ocean.length, ocean[0].length )
                && ocean[x][y-1] == '.'){
            return true;
        }
        if (isValid(x+1, y, ocean.length, ocean[0].length )
                && ocean[x+1][y] == '.'){
            return true;
        }
        if (isValid(x, y+1, ocean.length, ocean[0].length )
                && ocean[x][y+1] == '.'){
            return true;
        }
        return false;
    }

    private boolean isValid(int i, int j, int rowLength, int colLength) {
        return i>= 0 && i < rowLength && j >= 0 && j < colLength;
    }

    public static void main(String[] args) {
//        String[] islands = {"..OOOO..", ".OOOO...", "OOXOOOOO", "OOOOOOO.", ".OOOO...", "..OOO..."};
//        String[] instructions = {"W 3", "S 1", "E 2"};
        //, 		{0, 1}		Passed
        String[] islands ={"..", "XO"};
        String[] instructions = {"E 1", "W 1"};
        int[] result = new TreasureHunt().findTreasure(islands, instructions);
        System.out.println("======");
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
