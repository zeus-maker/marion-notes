package com.marion.gof.proxy;

/**
 * @author Marion
 * @date 2022/5/31 14:45
 */
public class RealPerson implements Person {

    @Override
    public void rentHouse() {
        System.out.println("[RealPerson] rentHouse");
    }

}
