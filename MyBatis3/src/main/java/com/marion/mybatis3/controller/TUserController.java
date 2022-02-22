package com.marion.mybatis3.controller;

import com.marion.mybatis3.entity.TUser;
import com.marion.mybatis3.service.TUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TUser)表控制层
 * @author makejava
 * @since 2022-01-04 13:09:48
 */
@RestController
@RequestMapping("tUser")
public class TUserController {
    /**
     * 服务对象
     */
    @Resource
    private TUserService tUserService;

    /**
     * 分页查询
     * @param tUser       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<TUser>> queryByPage(TUser tUser, PageRequest pageRequest) {
        return ResponseEntity.ok(this.tUserService.queryByPage(tUser, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TUser> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tUserService.queryById(id));
    }

    /**
     * 新增数据
     * @param tUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TUser> add(TUser tUser) {
        return ResponseEntity.ok(this.tUserService.insert(tUser));
    }

    /**
     * 编辑数据
     * @param tUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TUser> edit(TUser tUser) {
        return ResponseEntity.ok(this.tUserService.update(tUser));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.tUserService.deleteById(id));
    }

}

