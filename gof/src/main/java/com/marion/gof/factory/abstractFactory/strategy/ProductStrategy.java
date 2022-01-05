package com.marion.gof.factory.abstractFactory.strategy;

import com.marion.gof.factory.abstractFactory.BrandFactory;
import com.marion.gof.factory.abstractFactory.Product;

public interface ProductStrategy {

    void doProduct(BrandFactory brand, Product product);

}
