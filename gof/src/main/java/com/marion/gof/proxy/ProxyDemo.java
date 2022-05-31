package com.marion.gof.proxy;

import java.lang.reflect.Proxy;

/**
 * @author Marion
 * @date 2022/5/31 14:50
 */
public class ProxyDemo {

    public static void main(String[] args) {
        Person person = (Person)  Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Person.class},
                new JdkInvokeHandler<>(new RealPerson()));
        person.rentHouse();
    }

}
