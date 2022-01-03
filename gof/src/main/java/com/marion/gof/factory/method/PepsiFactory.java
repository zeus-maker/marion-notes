package com.marion.gof.factory.method;

public class PepsiFactory implements ColaFactory {
    @Override
    public ColaService product() {
        return new PepsiCola();
    }
}
