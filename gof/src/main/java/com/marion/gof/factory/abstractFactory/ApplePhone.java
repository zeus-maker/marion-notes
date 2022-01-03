package com.marion.gof.factory.abstractFactory;

public class ApplePhone implements Phone {
    @Override
    public void use() {
        System.out.println("使用苹果手机");
    }
}
