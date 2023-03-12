package com.example.hywm.controller;

import com.example.hywm.common.Result;
import com.example.hywm.common.WMContonst;
import com.example.hywm.entity.OrderDetail;
import com.example.hywm.service.OrdersService;
import com.example.hywm.vo.OrderVo;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping("/submit")
    public Result addOrder(@RequestBody OrderVo orderVo, HttpServletRequest req){
        try {
            String userId =(String) req.getSession().getAttribute("User");
            Boolean bool = ordersService.insertOrder(orderVo, userId);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_INSERT.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        } catch (Exception e) {
            return Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        }

    }

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
            List<OrderDetail> orderDetail = ordersService.selectOrderDetail(id);
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

    @GetMapping("/get")
    public Result getNumber(){
        try {
            String number = ordersService.getNumber();
            return Result.success(number);
        } catch (Exception e) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @GetMapping("/userPage")
    public Result queryOrderPage(PageReqVo pageReqVo,HttpServletRequest req){
        try {
            String id =(String) req.getSession().getAttribute("User");
            PageResult pageResult = ordersService.selectOrderPage(pageReqVo, id);
            return Result.success(pageResult);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }
}
