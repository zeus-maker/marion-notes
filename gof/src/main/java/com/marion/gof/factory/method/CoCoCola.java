package com.marion.gof.factory.method;

public class CoCoCola implements ColaService {
    @Override
    public void drink() {
        System.out.println("喝可口可乐");
    }
}
