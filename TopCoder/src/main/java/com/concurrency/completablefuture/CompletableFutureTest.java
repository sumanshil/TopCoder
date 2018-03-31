package com.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {


    public static String thenApplyTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
           try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               throw new IllegalStateException(e);
           }
            return "Rajeev";
        });

        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply((name) ->
            "Hello " + name
        );
        return greetingFuture.get();
    }

    public static String thenApplyChain() throws ExecutionException, InterruptedException {
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply((name) -> "Hello "+name).thenApply((greeting) -> greeting+", Welcome to the Callicoder blog");

        return whatsYourNameFuture.get();
    }

    public static Double thenCombine() throws ExecutionException, InterruptedException {
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm/100;
                    return weightInKg/(heightInMeter*heightInMeter);
                });

        return combinedFuture.get();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");

        });
        future.get();
        */
        //System.out.println(thenApplyChain());
        System.out.println(thenCombine());
    }
}
