package com.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/flatten-nested-list-iterator/
public class FlattenNestedListIterator {


      // This is the interface that allows for creating nested lists.
      // You should not implement it, or speculate about its implementation
      public interface NestedInteger {

          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger();

          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger();

          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return null if this NestedInteger holds a single integer
          public List<NestedInteger> getList();
      }

      static class NestedIntegerImpl implements NestedInteger {

          private List<NestedInteger> list = new LinkedList<>();

          public NestedIntegerImpl(List<NestedInteger> list) {
              this.list = list;
          }

          public NestedIntegerImpl(Integer val) {
              this.value = val;
          }
          private Integer value = -1;

          @Override
          public boolean isInteger() {
              return value != -1;
          }

          @Override
          public Integer getInteger() {
              return value;
          }

          @Override
          public List<NestedInteger> getList() {
              return list;
          }

          public void add (NestedInteger nestedInteger) {
              this.list.add(nestedInteger);
          }
      }

    public static class NestedIterator implements Iterator<Integer> {
        private List<Integer> current = new LinkedList<>();
        private int index = 0;
        public NestedIterator(List<NestedInteger> nestedList) {
            helper(nestedList, current);
        }

        @Override
        public Integer next() {
            return current.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index < current.size();
        }

        private void helper(List<NestedInteger> list, List<Integer> result) {

            for (NestedInteger nestedInteger : list) {
                if (nestedInteger.isInteger()) {
                    result.add(nestedInteger.getInteger());
                } else {
                    helper(nestedInteger.getList(), result);
                }
            }
        }
    }

    public static void main(String[] args) {
            List<NestedInteger> main = new LinkedList<>();
            NestedIntegerImpl firstList = new NestedIntegerImpl(new LinkedList<>());
            NestedInteger value = new NestedIntegerImpl(1);
            firstList.add(value);
            value = new NestedIntegerImpl(1);
            firstList.add(value);

            main.add(firstList);
            main.add(new NestedIntegerImpl(2));

            main.add(firstList);

            NestedIterator nestedIterator = new NestedIterator(main);

            while (nestedIterator.hasNext()) {
                System.out.println(nestedIterator.next());
            }


    }
}
