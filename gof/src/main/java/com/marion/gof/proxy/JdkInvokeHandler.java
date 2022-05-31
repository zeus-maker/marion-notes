package com.marion.gof.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Marion
 * @date 2022/5/31 14:47
 */
public class JdkInvokeHandler<T> implements InvocationHandler {

    private T target;

    public JdkInvokeHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object ret = null;

        System.out.println("[ProxyInvokeHandler] invoke before");

        try {
            ret = method.invoke(target, args);
        } catch (Exception ignored) {

        }

        System.out.println("[ProxyInvokeHandler] invoke after");

        return ret;
    }
}
