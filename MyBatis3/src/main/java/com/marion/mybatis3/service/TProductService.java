package com.marion.mybatis3.service;

import com.marion.mybatis3.entity.TProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (TProduct)表服务接口
 * @author makejava
 * @since 2022-01-04 16:02:48
 */
public interface TProductService {

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    TProduct queryById(Integer id);

    /**
     * 分页查询
     * @param tProduct    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<TProduct> queryByPage(TProduct tProduct, PageRequest pageRequest);

    /**
     * 新增数据
     * @param tProduct 实例对象
     * @return 实例对象
     */
    TProduct insert(TProduct tProduct);

    /**
     * 修改数据
     * @param tProduct 实例对象
     * @return 实例对象
     */
    TProduct update(TProduct tProduct);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
