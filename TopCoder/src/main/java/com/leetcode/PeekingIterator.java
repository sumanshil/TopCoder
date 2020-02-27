package com.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

//https://leetcode.com/problems/peeking-iterator/
public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> integerIterator;
    private Integer current = null;
    private Integer prev = null;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.integerIterator = iterator;
        try {
            current = integerIterator.next();
        } catch (NoSuchElementException e) {
            current = null;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        PeekingIterator iterator = new PeekingIterator(list.iterator());
        System.out.println(iterator.next());
        System.out.println(iterator.peek());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());

    }

    public Integer peek() {
        return current;
    }

    @Override
    public boolean hasNext() {
        return current != null || integerIterator.hasNext();
    }

    @Override
    public Integer next() {
        int temp = current;
        try {
            current = integerIterator.next();
        } catch (NoSuchElementException e) {
            current = null;
        }
        return temp;
    }
}
