package com.example.hywm.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/25 14:58
 **/
@Data
public class AddressBook implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    //用户id
    private String userId;

    //收货人
    private String consignee;

    //手机号
    private String phone;

    //性别 0 女 1 男
    private String sex;


    //详细地址
    private String detail;

    //标签
    private String label;

    //是否默认 0 否 1是
    private Integer isDefault;

    //创建时间
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createUser;

    private String updateUser;

    //是否删除
    private Integer isDeleted;
}
