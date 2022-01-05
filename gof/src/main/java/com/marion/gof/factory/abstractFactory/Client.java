package com.marion.gof.factory.abstractFactory;

public class Client {

    public static void main(String[] args) {
        /**
         * 小明购买苹果手机和苹果电脑：抽象工厂方法
         * 1. 定义抽象工厂，定义生产哪些产品
         * @see BrandFactory
         * 2. 定义具体工厂
         * 3. 定义抽象产品1
         * @see Phone
         * 4. 定义具体产品1
         * 5. 定义抽象产品2
         * @see Computer
         * 6. 定义具体产品2
         */
        AppleFactory appleFactory = new AppleFactory();
        appleFactory.produceComputer().use();
        appleFactory.producePhone().use();

        MiFactory miFactory = new MiFactory();
        miFactory.produceComputer().use();
        miFactory.producePhone().use();

    }

}
