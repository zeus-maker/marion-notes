package com.marion.gof.factory.sample;

public class Sprite implements DrinksService {
    @Override
    public void drink() {
        System.out.println("喝雪碧");
    }
}
