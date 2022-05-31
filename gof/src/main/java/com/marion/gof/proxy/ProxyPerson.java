package com.marion.gof.proxy;

/**
 * @author Marion
 * @date 2022/5/31 14:45
 */
public class ProxyPerson implements Person {

    private RealPerson target;

    public ProxyPerson(RealPerson target) {
        this.target = target;
    }

    @Override
    public void rentHouse() {
        System.out.println("[ProxyPerson] before");
        target.rentHouse();
        System.out.println("[ProxyPerson] after");
    }

}
