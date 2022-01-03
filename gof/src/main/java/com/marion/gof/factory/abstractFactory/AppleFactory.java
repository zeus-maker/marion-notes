package com.marion.gof.factory.abstractFactory;

public class AppleFactory implements BrandFactory {
    @Override
    public Phone producePhone() {
        return new ApplePhone();
    }

    @Override
    public Computer produceComputer() {
        return new AppleComputer();
    }
}
