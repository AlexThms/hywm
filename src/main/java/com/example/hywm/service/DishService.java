package com.example.hywm.service;

import com.example.hywm.dto.DishDto;
import com.example.hywm.entity.Dish;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 19:52
 **/
public interface DishService {
    PageResult selectDishPage(PageReqVo pageReqVo) throws Exception;

    Boolean insertDish(DishDto dishDto) throws Exception;

    Boolean editDish(DishDto dishDto) throws Exception;

    Boolean deleteDish(String id) throws Exception;

    DishDto selectDishById(String id) throws Exception;

    Boolean editDishStatus(String status,String id) throws Exception;

}
