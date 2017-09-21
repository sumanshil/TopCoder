package com.concurrency.threadpool;

public interface Future<T> {
    T get();
}
