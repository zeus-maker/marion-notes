package com.marion.gof.factory.sample;

public class Cola implements DrinksService {
    @Override
    public void drink() {
        System.out.println("喝可乐");
    }
}
