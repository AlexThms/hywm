package com.example.hywm.service.impl;

import com.example.hywm.common.PageUtils;
import com.example.hywm.entity.OrderDetail;
import com.example.hywm.entity.Orders;
import com.example.hywm.mapper.OrdersMapper;
import com.example.hywm.service.OrdersService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/18 12:30
 **/
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public PageResult selectOrdersPage(PageReqVo pageReqVo, String number, LocalDateTime beginTime, LocalDateTime endTime) throws Exception {
        int page = pageReqVo.getPage();
        int pageSize = pageReqVo.getPageSize();
        Page<Object> pageHelper = PageHelper.startPage(page, pageSize);
        List<Orders> ordersList = ordersMapper.selectOrdersPage();
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
        PageResult pageResult = PageUtils.getPageResult(pageInfo, pageHelper);
        return pageResult;
    }

    @Override
    public Boolean editOrderStatus(String status) throws Exception {
        Integer integer = ordersMapper.editOrderStatus(status);
        if(integer != 1){
            return false;
        }
        return true;
    }

    @Override
    public OrderDetail selectOrderDetail(String id) throws Exception {
        OrderDetail orderDetail = ordersMapper.selectOrderDetail(id);
        return orderDetail;
    }
}
