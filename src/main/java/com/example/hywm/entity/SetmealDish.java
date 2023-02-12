package com.example.hywm.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/12 16:06
 **/
@Data
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    //套餐id
    private String setmealId;

    //菜品id
    private String dishId;

    //菜品名称 （冗余字段）
    private String name;

    //菜品原价
    private BigDecimal price;

    //份数
    private Integer copies;

    //排序
    private Integer sort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createUser;

    private String updateUser;

    //是否删除
    private Integer isDeleted;
}
