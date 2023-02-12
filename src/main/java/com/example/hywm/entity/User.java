package com.example.hywm.entity;


import lombok.Data;

@Data
public class User {
    //主键
    private int id;
    private String userName;
    private String sex;
    private String phone;
    private String idNumber;
    private String avatar;
    private String status;

}

