package com.github.alexandrtokarenko.laba2.service;

import com.github.alexandrtokarenko.laba2.fun.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
public class CalucalatorService {

    private double totalSum = 0;
    private int finished = 0;
    private long time = 0;
    private double a = 1;
    private double b = 9;

    public void calculate(int numberOfIntervals, int numberOfThreads) throws ExecutionException, InterruptedException {
        finished = 0;
        totalSum = 0;
        long start = System.currentTimeMillis();
        long finish;

        double delta = (b - a) / numberOfThreads;
        ExecutorService es = Executors.newFixedThreadPool(numberOfThreads);

        List<Future<Double>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadNumber = i;
            futures.add(es.submit(new CalcCallable(a+threadNumber * delta,a + (threadNumber + 1) * delta,numberOfIntervals / numberOfThreads)));
        }

        for (Future<Double> future : futures) {
            totalSum += future.get();
        }

        finish = System.currentTimeMillis();
        time = finish - start;
        es.shutdown();

        System.out.println("Main Thread - " + Thread.currentThread().getName());
    }

    public double getTotalSum() {
        return totalSum;
    }

    public long getTime() {
        return time;
    }

}
