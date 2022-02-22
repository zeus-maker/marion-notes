package com.marion.mybatis3.service;

import com.marion.mybatis3.entity.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (TUser)表服务接口
 * @author makejava
 * @since 2022-01-04 13:09:48
 */
public interface TUserService {

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    TUser queryById(Integer id);

    /**
     * 分页查询
     * @param tUser       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<TUser> queryByPage(TUser tUser, PageRequest pageRequest);

    /**
     * 新增数据
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser insert(TUser tUser);

    /**
     * 修改数据
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser update(TUser tUser);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
