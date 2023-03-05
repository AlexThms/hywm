package com.example.hywm.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/25 14:52
 **/
@Data
public class ShoppingCard implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    //名称
    private String name;

    //用户id
    private String userId;

    //菜品id
    private String dishId;

    //套餐id
    private String setmealId;

    //口味
    private String dishFlavor;

    //数量
    private Integer number;

    //金额
    private BigDecimal amount;

    //图片
    private String image;

    private LocalDateTime createTime;
}
