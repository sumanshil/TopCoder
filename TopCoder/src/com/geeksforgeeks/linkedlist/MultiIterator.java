package com.geeksforgeeks.linkedlist;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sshil on 11/18/2015.
 */
public class MultiIterator implements Iterator<Integer> {

    private List<Iterator<Integer>> listOfIterators;
    private boolean isVertical;
    private Iterator<Integer> nextIterator;
    private int currentIteratorIndex = -1;
    private int totalIteratorCount = 0;

    public MultiIterator(Iterator<Iterator<Integer>> iterator,
                         boolean  isVertical) {
        listOfIterators = new ArrayList<>();
        while ((iterator.hasNext())) {
            listOfIterators.add(iterator.next());
        }
        totalIteratorCount = listOfIterators.size();
        int index = getNextIterator(currentIteratorIndex);
        currentIteratorIndex = index;
        if (index != -1){
            nextIterator = listOfIterators.get(index);
        }
    }


    @Override
    public boolean hasNext() {
        return nextIterator != null;
    }

    @Override
    public Integer next() {
        int retVal = nextIterator.next();
        int index = getNextIterator(currentIteratorIndex);
        if ( index != -1){
            nextIterator = listOfIterators.get(index);
            currentIteratorIndex = index;
        } else {
            nextIterator = null;
        }
        return  retVal;

    }

    private int getNextIterator(int index) {
        int originalIndex = index;
        int retVal = -1;
        while(true) {
            index = (index+1)%totalIteratorCount;
            if (listOfIterators.get(index).hasNext()){
                retVal =  index;
                break;
            }
            if (index == originalIndex){
                break;
            }
        }
        return  retVal;
    }

    interface FindNextIteratorStrategy {
        Iterator<Integer> findNextIterator();
    }

    class VerticalIteratorStrategy implements  FindNextIteratorStrategy {
        private List<Iterator<Integer>> list = null;
        private int currentIndex = 0;
        private Iterator<Integer> nextIterator;
        public VerticalIteratorStrategy(List<Iterator<Integer>> iteratorList){
            list = iteratorList;
            currentIndex = 0;

        }

        private int getNextIterator(int index) {
            int originalIndex = index;
            int retVal = -1;
            while(true) {
                index = (index+1)%totalIteratorCount;
                if (listOfIterators.get(index).hasNext()){
                    retVal =  index;
                    break;
                }
                if (index == originalIndex){
                    break;
                }
            }
            return  retVal;
        }

        @Override
        public Iterator<Integer> findNextIterator() {
            return null;
        }
    }

    class HorizontalIteratorStrategy implements FindNextIteratorStrategy {
        private List<Iterator<Integer>> list = null;
        private int currentIndex = 0;
        public HorizontalIteratorStrategy(List<Iterator<Integer>> iteratorList){
            list = iteratorList;
            currentIndex = 0;

        }

        @Override
        public Iterator<Integer> findNextIterator() {
            return null;
        }
    }
    public static void main(String[] args) {
        List<Integer> a = new LinkedList<>();
        a.add(1);
        a.add(7);
        a.add(13);
        a.add(17);
        List<Integer> b = new LinkedList<>();
        b.add(2);
        b.add(8);
        b.add(14);
        b.add(18);
        List<Integer> c = new LinkedList<>();
        c.add(3);
        c.add(9);
        List<Integer> d = new LinkedList<>();
        d.add(4);
        d.add(10);
        d.add(15);
        List<Integer> e = new LinkedList<>();
        e.add(5);
        e.add(11);
        List<Integer> f = new LinkedList<>();
        f.add(6);
        f.add(12);
        f.add(16);
        f.add(19);
        List<Iterator<Integer>> iterators = new LinkedList<>();
        iterators.add(a.iterator());
        iterators.add(b.iterator());
        iterators.add(c.iterator());
        iterators.add(d.iterator());
        iterators.add(e.iterator());
        iterators.add(f.iterator());
        Iterator<Integer> it = new MultiIterator(iterators.iterator(),true);
        while (it.hasNext()) {
            System.out.print(it.next() + ",");
            // prints: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
        }
    }
}
