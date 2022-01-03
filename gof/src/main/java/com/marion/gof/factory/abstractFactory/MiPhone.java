package com.marion.gof.factory.abstractFactory;

public class MiPhone implements Phone {
    @Override
    public void use() {
        System.out.println("使用小米手机");
    }
}
