package com.marion.gof.factory.abstractFactory.demo2.strategy;

import com.marion.gof.factory.abstractFactory.BrandFactory;
import com.marion.gof.factory.abstractFactory.Product;

public class BuyProductStrategy implements ProductStrategy {

    @Override
    public void doProduct(BrandFactory brand, Product product) {
        // TODO: 2022/1/5 扣除资产
        // TODO: 2022/1/5 记录品牌销量
        System.out.println("购买成功");
    }
}
