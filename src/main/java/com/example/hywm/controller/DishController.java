package com.example.hywm.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.hywm.common.Result;
import com.example.hywm.common.WMContonst;
import com.example.hywm.dto.DishDto;
import com.example.hywm.entity.Dish;
import com.example.hywm.service.DishService;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Description 菜品管理
 * Author lihao
 *
 * @Date 2023/1/15 19:51
 **/
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {


    @Autowired
    DishService dishService;

    @GetMapping("/page")
    public Result queryDishPage(PageReqVo pageReqVo){
        try {
            PageResult pageResult = dishService.selectDishPage(pageReqVo);
            log.info("分页查询返回：{}",pageResult);
            return Result.success(pageResult);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @GetMapping("/{id}")
    public Result queryDishById(@PathVariable String id){
        try {
            Dish dish = dishService.selectDishById(id);
            return Result.success(dish);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @PostMapping
    public Result insertDish(HttpServletRequest req, @RequestBody DishDto dishDto){
        try {
            String id =(String) req.getSession().getAttribute("Employee");
            dishDto.setCreateUser(id);
            dishDto.setUpdateUser(id);
            Boolean bool = dishService.insertDish(dishDto);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_INSERT.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        }
    }

    @PutMapping
    public Result editDish(HttpServletRequest req, @RequestBody DishDto dishDto){
        try {
            String id =(String) req.getSession().getAttribute("Employee");
            dishDto.setUpdateUser(id);
            Boolean bool = dishService.editDish(dishDto);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }

    @DeleteMapping
    public Result deleteDish(@RequestParam("ids") String id){
        try {
            Boolean bool = dishService.deleteDish(id);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_DELETE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        }
    }

    @PostMapping("/status/{status}")
    public Result editDishStatus(@PathVariable String status,@RequestParam("ids") String id){
        try {
            Boolean bool = dishService.editDishStatus(status,id);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }


}
