package com.marion.gof.factory.abstractFactory.demo2;

import com.marion.gof.enums.OperateType;
import com.marion.gof.factory.abstractFactory.demo2.strategy.BuyProductStrategy;
import com.marion.gof.factory.abstractFactory.demo2.strategy.ProductContext;
import com.marion.gof.factory.abstractFactory.demo2.strategy.RefundProductStrategy;

/**
 * 策略模式+抽象工厂模式购买商品案例
 */
public class Client2 {

    public static void main(String[] args) {

        System.out.println("==============策略模式+抽象工厂模式购买商品案例=============");

        // 购买
        doProduct(1, 1, 1);

        // 退款
        doProduct(1, 1, 2);
    }

    private static void doProduct(int brandType, int level, int type) {
        /**
         * 策略模式+抽象工厂模式购买商品案例
         * 1. 定义抽象策略（商品操作）
         * @see com.marion.gof.factory.abstractFactory.demo2.strategy.ProductStrategy
         * 2. 定义具体策略（购买、退款）
         * @see BuyProductStrategy
         * @see RefundProductStrategy
         * 3. 定义上下文
         * @see ProductContext
         */
        OperateType operateType = OperateType.fromValue(type);

        ProductContext productContext;
        switch (operateType) {
            case BUY:
                productContext = new ProductContext(new BuyProductStrategy());
                break;
            case REFUND:
                productContext = new ProductContext(new RefundProductStrategy());
                break;
            default:
                return;
        }

        productContext.doProduct(brandType, level);
    }


}
