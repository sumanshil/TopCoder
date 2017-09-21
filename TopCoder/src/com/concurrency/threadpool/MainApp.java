package com.concurrency.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainApp {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(5);

        Callable<String> callable1 = () -> {
            Thread.sleep(10000);
            return "String "+1;
        };

        Callable<String> callable2 = () -> {
            Thread.sleep(10000);
            return "String "+2;
        };

        Callable<String> callable3 = () -> {
            Thread.sleep(10000);
            return "String "+3;
        };

        Callable<String> callable4 = () -> {
            Thread.sleep(10000);
            return "String "+4;
        };

        Callable<String> callable5 = () -> {
            Thread.sleep(10000);
            return "String "+5;
        };

        List<Future<String>> list = new ArrayList<>();
        Future<String> future = pool.submit(callable1);
        list.add(future);
        future = pool.submit(callable2);
        list.add(future);
        future = pool.submit(callable3);
        list.add(future);
        future = pool.submit(callable4);
        list.add(future);
        future = pool.submit(callable5);
        list.add(future);
        pool.stop();

        for (Future<String> f : list) {
            String result = f.get();
            System.out.println(result);
        }
    }
}
