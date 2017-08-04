package com.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sshil on 7/18/17.
 */
public class PrintOddAndEven {

    static class OddNumberPrinter implements Runnable{

        private Value value;

        public OddNumberPrinter(Value value){
            this.value = value;
        }

        @Override
        public void run() {
            try {
                value.printOdd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class EvenNumberPrinter implements Runnable {

        private Value value;

        public EvenNumberPrinter (Value value){
            this.value = value;
        }
        @Override
        public void run() {
            try {
                value.printEven();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Value {
        private int value = 0;
        private Lock lock = new ReentrantLock();

        private Condition oddCondition = lock.newCondition();
        private Condition evenCondition = lock.newCondition();

        public void printOdd() throws InterruptedException {
            while (true) {
                try {
                    lock.lock();
                    if (value == 10){
                        return;
                    }
                    while (value % 2 == 0) {
                        oddCondition.await();
                    }
                    System.out.println("Printing odd number " + value);
                    value = value + 1;
                    evenCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }


        public void printEven() throws InterruptedException {
            while (true) {
                try {
                    lock.lock();
                    if (value == 10){
                        return;
                    }
                    while (value % 2 != 0) {
                        evenCondition.await();
                    }
                    System.out.println("Printing even number " + value);
                    value = value + 1;
                    oddCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        public static void main(String[] args) {
            Value value = new Value();
            Runnable oddPrinter = new OddNumberPrinter(value);
            Runnable evenPrinter = new EvenNumberPrinter(value);
            Thread oddThread = new Thread(oddPrinter);
            Thread evenThread = new Thread(evenPrinter);
            oddThread.start();
            evenThread.start();
        }
    }




}
