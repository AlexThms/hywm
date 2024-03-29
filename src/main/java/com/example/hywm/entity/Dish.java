package com.example.hywm.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 18:43
 **/
@Data
public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String categoryId;

    private BigDecimal price;

    private String code;

    private String image;

    private String description;

    private int sort;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createUser;

    private String updateUser;

    private String isDelete;
}
