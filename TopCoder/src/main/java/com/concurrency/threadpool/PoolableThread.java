package com.concurrency.threadpool;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PoolableThread<T> extends Thread {

    private Queue<Task<T>> queue = new ConcurrentLinkedQueue<>();
    private boolean stop = false;
    private int id = 0;

    public PoolableThread(Queue<Task<T>> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run(){
        while (!stop) {
          //  System.out.println("Thread "+id+" is waiting");
            Task<T> task = queue.poll();
           // System.out.println("Thread "+id+" got a task");
            if (task != null) {
                Callable<T> callable = task.getCallable();
                try {
                    T result = callable.call();
                    Future<T> future =task.getFuture();
                    ((FutureImpl<T>)future).set(result);
                } catch (Exception e) {
                }
            }
        }

    }

    public void stopRunning(){
        this.stop = true;
    }
}
