package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DesignASnakeGame {

    static class SnakeGame {

        class SnakePosition {
            int x;
            int y;

            public SnakePosition(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof SnakePosition)) {
                    return false;
                }
                SnakePosition that = (SnakePosition) o;
                return x == that.x &&
                       y == that.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }
        }


        private List<SnakePosition> snakePositions = new LinkedList<>();
        private Set<SnakePosition> currentPositions = new HashSet<>();
        int foodIndex = 0;
        int[][] foodPositions = null;
        int width = 0;
        int height = 0;

        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. **/
        public SnakeGame(int width, int height, int[][] food) {
            snakePositions.add(new SnakePosition(0, 0));
            currentPositions.add(new SnakePosition(0, 0));
            this.foodPositions = food;
            this.width = width;
            this.height = height;
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. **/
        public int move(String direction) {
            SnakePosition currentPosition = snakePositions.get(snakePositions.size()-1);
            int newPositionX = 0;
            int newPositionY = 0;
            if ("U".equals(direction)) {
                newPositionX = currentPosition.x - 1;
                newPositionY = currentPosition.y;
            } else if ("L".equals(direction)) {
                newPositionX = currentPosition.x;
                newPositionY = currentPosition.y - 1;
            } else if ("R".equals(direction)) {
                newPositionX = currentPosition.x;
                newPositionY = currentPosition.y + 1;
            } else if ("D".equals(direction)) {
                newPositionX = currentPosition.x + 1;
                newPositionY = currentPosition.y;
            }

            if (newPositionX < 0 || newPositionX == height || newPositionY < 0
                || newPositionY == width) {
                return -1;
            }
            if (foodIndex < foodPositions.length &&
                newPositionX == this.foodPositions[foodIndex][0] && newPositionY == this.foodPositions[foodIndex][1]) {
                foodIndex++;
            } else {
                SnakePosition oldPosition = snakePositions.get(0);
                snakePositions.remove(0);
                currentPositions.remove(new SnakePosition(oldPosition.x, oldPosition.y));
            }
            if (currentPositions.contains(new SnakePosition(newPositionX, newPositionY))) {
                return -1;
            }
            snakePositions.add(new SnakePosition(newPositionX, newPositionY));
            currentPositions.add(new SnakePosition(newPositionX, newPositionY));

            return foodIndex;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{2, 0},{0, 0}, {0, 2}, {2, 2}};
        SnakeGame snake = new SnakeGame(3, 3, arr);
        System.out.println(snake.move("D"));
        System.out.println(snake.move("D"));
        System.out.println(snake.move("R"));

        System.out.println(snake.move("U"));
        System.out.println(snake.move("U"));
        System.out.println(snake.move("L"));

        System.out.println(snake.move("D"));
        System.out.println(snake.move("R"));
        System.out.println(snake.move("R"));
        System.out.println(snake.move("U"));
        System.out.println(snake.move("L"));
        System.out.println(snake.move("D"));

    }
}
