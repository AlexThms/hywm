package com.example.hywm.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    //主键
    private String id;

    private String name;

    private String sex;

    private String phone;

    private String idNumber;

    private String avatar;

    private String status;

}

