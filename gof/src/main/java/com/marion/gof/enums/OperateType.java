package com.marion.gof.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperateType {

    /**
     * 1-购买（扣钱）
     * 2-退款（加钱）
     */
    BUY(1),
    REFUND(2);

    private int value;

    public static OperateType fromValue(int value) {
        for (OperateType type : OperateType.values()) {
            if (value == type.getValue()) {
                return type;
            }
        }
        return null;
    }
}
