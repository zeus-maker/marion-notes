package com.marion.gof.factory.abstractFactory.demo2.strategy;

import com.marion.gof.factory.abstractFactory.AppleFactory;
import com.marion.gof.factory.abstractFactory.BrandFactory;
import com.marion.gof.factory.abstractFactory.MiFactory;
import com.marion.gof.factory.abstractFactory.Product;

public class ProductContext {

    private ProductStrategy strategy;

    public ProductContext(ProductStrategy strategy) {
        this.strategy = strategy;
    }

    public void doProduct(int brandType, int level) {
        BrandFactory brand;
        if (brandType == 1) {
            brand = new AppleFactory();
        } else if (brandType == 2) {
            brand = new MiFactory();
        } else {
            System.out.println("没有该品牌");
            return;
        }

        Product product;
        if (level == 1) {
            product = brand.producePhone();
        } else if (brandType == 2) {
            product = brand.produceComputer();
        } else {
            System.out.println("没有该种类");
            return;
        }

        strategy.doProduct(brand, product);
    }

}
