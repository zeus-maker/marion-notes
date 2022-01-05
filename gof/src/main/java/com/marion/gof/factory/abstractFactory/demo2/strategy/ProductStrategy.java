package com.marion.gof.factory.abstractFactory.demo2.strategy;

import com.marion.gof.factory.abstractFactory.BrandFactory;
import com.marion.gof.factory.abstractFactory.Product;

public interface ProductStrategy {

    void doProduct(BrandFactory brand, Product product);

}
