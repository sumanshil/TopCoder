package com.concurrency.threadpool;

import java.util.concurrent.Callable;

public class Task<T> {
    private Future<T> future;
    private Callable<T> callable;

    public Task(Future<T> future, Callable<T> callable){
        this.future = future;
        this.callable = callable;
    }

    public Future<T> getFuture() {
        return future;
    }

    public void setFuture(Future<T> future) {
        this.future = future;
    }

    public Callable<T> getCallable() {
        return callable;
    }

    public void setCallable(Callable<T> callable) {
        this.callable = callable;
    }
}
