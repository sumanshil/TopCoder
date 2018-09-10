package com.leetcode.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularQueue<T> {

    private List<T> list;
    private int maxSize;
    private int front;
    private int rear;

    public CircularQueue(int maxSize) {
        list = new ArrayList<>();
        this.maxSize = maxSize;
        front = 0;
        rear = 0;
    }


    public Optional<T> front() {
        if (this.front == this.rear) {
            return Optional.empty();
        }
        T retVal = list.get(front);
        /*
        list.set(front, null);
        front++;
        if (front == maxSize) {
            front = 0;
        }
        */
        return Optional.of(retVal);
    }

    public Optional<T> rear() {
        if (this.front == this.rear) {
            return Optional.empty();
        }

        T retVal =  list.get(rear);
        /*
        list.set(rear, null);
        rear--;
        if (rear < 0) {
            rear = maxSize - 1;
        }
        */
        return Optional.of(retVal);
    }

    public boolean enqueue(T value) {
        if (isFull()) {
            return false;
        }
        int rear = (this.rear + 1) % maxSize;
        list.set(rear, value);
        this.rear = rear;
        return true;
    }

    public T dequeue() {
        if ( (front + 1) % maxSize == this.rear ) {
            return  null;
        }
        T retVal = list.get(this.front);
        front = (front + 1) % maxSize;
        return retVal;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean isFull() {
        return this.rear + 1 % maxSize == this.front;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return null;
    }
}
