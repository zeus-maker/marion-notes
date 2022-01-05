package com.marion.gof.strategy;

public class CalculatorContext {

    private Strategy strategy;

    public CalculatorContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public int compute(int num1, int num2) {
        return strategy.compute(num1, num2);
    }

}
