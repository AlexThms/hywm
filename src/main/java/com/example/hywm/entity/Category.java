package com.example.hywm.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 15:54
 **/
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String type;

    private String name;

    private int sort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createUser;

    private String updateUser;
}
