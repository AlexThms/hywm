package com.example.hywm.controller;

import com.example.hywm.common.Result;
import com.example.hywm.common.WMContonst;
import com.example.hywm.entity.OrderDetail;
import com.example.hywm.service.OrdersService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/18 12:29
 **/
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/page")
    public Result queryOrdersPage(PageReqVo pageReqVo, String number, LocalDateTime beginTime,LocalDateTime endTime){
        try {
            PageResult pageResult = ordersService.selectOrdersPage(pageReqVo,number,beginTime,endTime);
            return Result.success(pageResult);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @GetMapping("/orderDetail/{id}")
    public Result queryOrderDetail(@PathVariable String id){
        try {
            OrderDetail orderDetail = ordersService.selectOrderDetail(id);
            return Result.success(orderDetail);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @PutMapping
    public Result editOrderStatus(String status){
        try {
            Boolean bool = ordersService.editOrderStatus(status);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }
}
