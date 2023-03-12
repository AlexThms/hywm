package com.example.hywm.service;

import com.example.hywm.entity.OrderDetail;
import com.example.hywm.vo.OrderVo;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/18 12:30
 **/
public interface OrdersService {

    Boolean insertOrder(OrderVo orderVo,String userId) throws Exception;

    PageResult selectOrdersPage(PageReqVo pageReqVo, String number, LocalDateTime beginTime, LocalDateTime endTime) throws Exception;

    Boolean editOrderStatus(String status) throws Exception;

    List<OrderDetail> selectOrderDetail(String id) throws Exception;

    String getNumber() throws Exception;

    PageResult selectOrderPage(PageReqVo pageReqVo,String id) throws Exception;
}
