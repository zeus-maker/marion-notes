package com.marion.gof.factory.abstractFactory;

import com.marion.gof.enums.OperateType;
import com.marion.gof.factory.abstractFactory.strategy.BuyProductStrategy;
import com.marion.gof.factory.abstractFactory.strategy.ProductContext;
import com.marion.gof.factory.abstractFactory.strategy.RefundProductStrategy;
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
         * 1. 选择不同品牌不同商品，购买【苹果】+【手机】
         * 2. 定义抽象策略1（获得品牌）
         * 3. 定义抽象策略2（获得产品）
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
