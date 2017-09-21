package com.concurrency.threadpool;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {

    private Queue<Task> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    private PoolableThread<Task> pool[];
    private boolean stop = false;

    public ThreadPool(int size) {
        pool = new PoolableThread[size];
        for (int i = 0; i < size; i++) {
            pool[i] = new PoolableThread(concurrentLinkedQueue, i+1);
            pool[i].start();
        }
    }

    public <T> Future<T> submit(Callable<T> callable) {
        if (!stop) {
            Task<T> task = new Task<>(new FutureImpl<T>(), callable);
            concurrentLinkedQueue.add(task);
            return task.getFuture();
        } else {
            throw new RuntimeException("Thread pool is stopped");
        }
    }

    public void stop() {
        this.stop = true;
        for (int i = 0; i < pool.length; i++) {
            pool[i].stopRunning();
        }
    }
}
