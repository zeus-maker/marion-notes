package com.marion.mybatis3.dao;

import com.marion.mybatis3.entity.TUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (TUser)表数据库访问层
 * @author makejava
 * @since 2022-01-04 13:09:48
 */
public interface TUserDao {

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    TUser queryById(Integer id);

    /**
     * 查询指定行数据
     * @param tUser    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<TUser> queryAllByLimit(TUser tUser, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     * @param tUser 查询条件
     * @return 总行数
     */
    long count(TUser tUser);

    /**
     * 新增数据
     * @param tUser 实例对象
     * @return 影响行数
     */
    int insert(TUser tUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     * @param entities List<TUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     * @param entities List<TUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TUser> entities);

    /**
     * 修改数据
     * @param tUser 实例对象
     * @return 影响行数
     */
    int update(TUser tUser);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

