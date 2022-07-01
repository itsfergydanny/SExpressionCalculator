package com.sysadamant.sexpressioncalculator;

import com.sysadamant.sexpressioncalculator.evaluator.Evaluator;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Evaluator().eval(args));
    }
}