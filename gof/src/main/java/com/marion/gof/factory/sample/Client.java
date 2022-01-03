package com.marion.gof.factory.sample;

public class Client {

    public static void main(String[] args) {
        /**
         * 喝饮料
         * 1. 定义抽象产品
         * 2. 定义具体产品
         * 3. 定义工厂
         */
        DrinksFactory drinksFactory = new DrinksFactory();

        DrinksService colaService = drinksFactory.productDrinks(Cola.class);
        colaService.drink();

        DrinksService spriteService = drinksFactory.productDrinks(Sprite.class);
        spriteService.drink();
    }

}
