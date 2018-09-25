package com.leetcode.integer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//https://leetcode.com/problems/self-dividing-numbers/description/
public class SelfDividingNumbers {

    public List<Integer> selfDividingNumbers(int left, int right) {
        return IntStream.range(left, right + 1)
                .filter(SelfDividingNumbers::isSelfDividing)
                .boxed()
                .collect(Collectors.toList());
    }

    private static boolean isSelfDividing(int number) {
        int integer = number;
        while (integer > 0) {
            int number1 = integer/10;
            int remainder = integer % 10;
            if (remainder == 0) {
                return false;
            }
            if (number % remainder != 0) {
                return false;
            }
            integer = number1;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> result
                = new SelfDividingNumbers().selfDividingNumbers(1, 22);
        result.stream().forEach(System.out::println);
    }
}
