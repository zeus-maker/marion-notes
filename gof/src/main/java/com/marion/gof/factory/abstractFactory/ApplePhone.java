package com.marion.gof.factory.abstractFactory;

public class ApplePhone extends AppleFactory implements Phone {
    @Override
    public void use() {
        System.out.println("使用苹果手机");
    }
}
