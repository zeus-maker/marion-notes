package com.marion.gof.factory.method;

public class Client {

    public static void main(String[] args) {
        /**
         * 工厂方法：喝不同厂商的可乐
         * 1. 定义抽象产品
         * 2. 定义具体产品
         * 3. 定义抽象工厂
         * 4. 定义具体工厂
         * 5. 选择
         */

        ColaService pepsi = new PepsiFactory().product();
        pepsi.drink();

        ColaService coco = new CoCoFactory().product();
        coco.drink();
    }

}
