package com.marion.mybatis3.dao;

import com.marion.mybatis3.entity.TProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (TProduct)表数据库访问层
 * @author makejava
 * @since 2022-01-04 16:02:47
 */
public interface TProductDao {

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    TProduct queryById(Integer id);

    /**
     * 查询指定行数据
     * @param tProduct 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<TProduct> queryAllByLimit(TProduct tProduct, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     * @param tProduct 查询条件
     * @return 总行数
     */
    long count(TProduct tProduct);

    /**
     * 新增数据
     * @param tProduct 实例对象
     * @return 影响行数
     */
    int insert(TProduct tProduct);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     * @param entities List<TProduct> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TProduct> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     * @param entities List<TProduct> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TProduct> entities);

    /**
     * 修改数据
     * @param tProduct 实例对象
     * @return 影响行数
     */
    int update(TProduct tProduct);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

