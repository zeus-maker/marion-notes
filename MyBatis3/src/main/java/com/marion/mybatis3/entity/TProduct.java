package com.marion.mybatis3.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * (TProduct)实体类
 * @author makejava
 * @since 2022-01-04 16:02:47
 */
public class TProduct implements Serializable {
    private static final long serialVersionUID = 904186002269703180L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 商品名称
     */
    private String title;
    /**
     * 库存
     */
    private Integer inventory;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

}

