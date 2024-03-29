package com.example.hywm.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/18 12:21
 **/
@Data
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    //订单号
    private String number;

    //订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
    private int status;


    //下单用户id
    private String userId;

    //地址id
    private String addressBookId;


    //下单时间
    private LocalDateTime orderTime;


    //实收金额
    private BigDecimal amount;

    //备注
    private String remark;

    //用户名
    private String userName;

    //手机号
    private String phone;

    //地址
    private String address;

    //收货人
    private String consignee;

    //订单类型 0:堂食 1:外卖
    private int type;
}
