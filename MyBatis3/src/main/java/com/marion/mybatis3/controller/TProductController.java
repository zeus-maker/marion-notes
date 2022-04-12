package com.marion.mybatis3.controller;

import com.marion.mybatis3.entity.TProduct;
import com.marion.mybatis3.service.TProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TProduct)表控制层
 * @author makejava
 * @since 2022-01-04 16:02:47
 */
@RestController
@RequestMapping("tProduct")
public class TProductController {
    /**
     * 服务对象
     */
    @Resource
    private TProductService tProductService;

    /**
     * 分页查询
     * @param tProduct    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<TProduct>> queryByPage(TProduct tProduct, PageRequest pageRequest) {
        return ResponseEntity.ok(this.tProductService.queryByPage(tProduct, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TProduct> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tProductService.queryById(id));
    }

    /**
     * 新增数据
     * @param tProduct 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TProduct> add(TProduct tProduct) {
        return ResponseEntity.ok(this.tProductService.insert(tProduct));
    }

    /**
     * 编辑数据
     * @param tProduct 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TProduct> edit(TProduct tProduct) {
        return ResponseEntity.ok(this.tProductService.update(tProduct));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        MultiValueMap
        return ResponseEntity.ok(this.tProductService.deleteById(id));
    }

}

