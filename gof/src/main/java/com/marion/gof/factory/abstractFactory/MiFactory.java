package com.marion.gof.factory.abstractFactory;

public class MiFactory implements BrandFactory {
    @Override
    public Phone producePhone() {
        return new MiPhone();
    }

    @Override
    public Computer produceComputer() {
        return new MiComputer();
    }
}
