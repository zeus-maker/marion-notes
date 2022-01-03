package com.marion.gof.factory.abstractFactory;

public class AppleComputer implements Computer {
    @Override
    public void use() {
        System.out.println("使用苹果电脑");
    }
}
