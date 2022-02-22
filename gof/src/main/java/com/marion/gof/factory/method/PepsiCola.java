package com.marion.gof.factory.method;

public class PepsiCola implements ColaService {
    @Override
    public void drink() {
        System.out.println("喝百事可乐");
    }
}
