package com.example.hywm.entity;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 员工实体
 */
@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createUser;

    private String updateUser;
}
