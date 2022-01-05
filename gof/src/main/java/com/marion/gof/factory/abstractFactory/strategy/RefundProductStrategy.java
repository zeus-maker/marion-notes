package com.marion.gof.factory.abstractFactory.strategy;

import com.marion.gof.factory.abstractFactory.BrandFactory;
import com.marion.gof.factory.abstractFactory.Product;

public class RefundProductStrategy implements ProductStrategy {

    @Override
    public void doProduct(BrandFactory brand, Product product) {
        System.out.println("退款成功");
    }
}
