package com.marion.gof.strategy;

public class Client {

    public static void main(String[] args) {
        /**
         * 计算器
         * 1. 定义抽象策略（两数计算）
         * 2. 定义具体策略（加法、减法）
         * 3. 定义上下文
         */

        int num1 = 10;
        int num2 = 10;
        CalculatorContext addContext = new CalculatorContext(new AddStrategyImpl());
        // 1. 加法
        int add = addContext.compute(num1, num2);

        // 2. 减法
        CalculatorContext subtractContext = new CalculatorContext(new SubtractStrategyImpl());
        int subtract = subtractContext.compute(num1, num2);

        // 3. 乘法
        CalculatorContext multiplyContext = new CalculatorContext(new MultiplyStrategyImpl());
        int multiply = multiplyContext.compute(num1, num2);

        System.out.println("10 + 10=" + add + ", 10 - 10=" + subtract + ", 10 * 10=" + multiply);
    }

}
