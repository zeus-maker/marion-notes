package com.marion.mybatis3.service.impl;

import com.marion.mybatis3.entity.TProduct;
import com.marion.mybatis3.dao.TProductDao;
import com.marion.mybatis3.service.TProductService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (TProduct)表服务实现类
 * @author makejava
 * @since 2022-01-04 16:02:48
 */
@Service("tProductService")
public class TProductServiceImpl implements TProductService {
    @Resource
    private TProductDao tProductDao;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TProduct queryById(Integer id) {
        return this.tProductDao.queryById(id);
    }

    /**
     * 分页查询
     * @param tProduct    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<TProduct> queryByPage(TProduct tProduct, PageRequest pageRequest) {
        long total = this.tProductDao.count(tProduct);
        return new PageImpl<>(this.tProductDao.queryAllByLimit(tProduct, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     * @param tProduct 实例对象
     * @return 实例对象
     */
    @Override
    public TProduct insert(TProduct tProduct) {
        this.tProductDao.insert(tProduct);
        return tProduct;
    }

    /**
     * 修改数据
     * @param tProduct 实例对象
     * @return 实例对象
     */
    @Override
    public TProduct update(TProduct tProduct) {
        this.tProductDao.update(tProduct);
        return this.queryById(tProduct.getId());
    }

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tProductDao.deleteById(id) > 0;
    }
}
