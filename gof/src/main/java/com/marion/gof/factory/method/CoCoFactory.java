package com.marion.gof.factory.method;

public class CoCoFactory implements ColaFactory {
    @Override
    public ColaService product() {
        return new CoCoCola();
    }
}
