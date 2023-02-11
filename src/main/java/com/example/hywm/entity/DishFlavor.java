package com.example.hywm.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/9 21:14
 **/
@Data
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    //菜品id
    private String dishId;

    //口味名称
    private String name;

    //口味数据list
    private String value;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createUser;

    private String updateUser;

    //是否删除
    private String isDeleted;

}
