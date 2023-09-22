package com.github.alexandrtokarenko.laba2.service;

import com.github.alexandrtokarenko.laba2.fun.Function;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalcCallable implements Callable<Double> {

    private int numberOfIntervals;
    private int numberOfThreads;
    private int n;
    private double a = 1;
    private double b = 9;
    private double totalSum = 0;
    private int finished = 0;

    public CalcCallable() {
    }

    public CalcCallable(int numberOfIntervals, int numberOfThreads) {
        this.numberOfIntervals = numberOfIntervals;
        this.numberOfThreads = numberOfThreads;
    }

    public CalcCallable(double a, double b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    @Override
    public Double call() throws Exception {
     /*   double delta = (b-a)/numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            System.out.println("Scenario writer thread - " + Thread.currentThread().getName());
            calculate(a+i*delta,a+(i+1)*delta,numberOfIntervals/numberOfThreads);
        }

        return totalSum;*/
        System.out.println("thread - " + Thread.currentThread().getName());
        calculate(a,b,n);
        return totalSum;
    }

    public void calculate(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = (Function.calc(a) + Function.calc(b)) * 0.5;
        double preSum = 0;
        for (int i = 1; i < n; i += 1) {
            preSum += Function.calc(a + i * h);
        }
        sum += preSum;
        preSum = 0;
        for (int i = 1; i <= n; i += 1) {
            preSum += Function.calc(((a + i * h) + (a + (i-1) * h))/2);
        }
        sum += preSum * 2;
        totalSum += (h / 3) * sum;
    }

    public double getTotalSum() {
        return totalSum;
    }
}
