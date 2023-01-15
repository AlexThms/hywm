package com.example.hywm.controller;

import com.example.hywm.common.Result;
import com.example.hywm.common.WMContonst;
import com.example.hywm.entity.Dish;
import com.example.hywm.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Description todo
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
    public Result queryDishPage(){
        try {
            dishService.selectDishPage();
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
        return null;
    }

    public Result insertDish(HttpServletRequest req, @RequestBody Dish dish){
        try {
            dishService.insertDish(dish);
            return null;
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        }
    }
}
