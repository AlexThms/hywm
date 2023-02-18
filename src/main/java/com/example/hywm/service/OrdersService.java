package com.example.hywm.service;

import com.example.hywm.entity.OrderDetail;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;

import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/18 12:30
 **/
public interface OrdersService {

    PageResult selectOrdersPage(PageReqVo pageReqVo, String number, LocalDateTime beginTime, LocalDateTime endTime) throws Exception;

    Boolean editOrderStatus(String status) throws Exception;

    OrderDetail selectOrderDetail(String id) throws Exception;
}
