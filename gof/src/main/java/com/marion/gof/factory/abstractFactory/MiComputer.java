package com.marion.gof.factory.abstractFactory;

public class MiComputer implements Computer {
    @Override
    public void use() {
        System.out.println("使用小米电脑");
    }
}
