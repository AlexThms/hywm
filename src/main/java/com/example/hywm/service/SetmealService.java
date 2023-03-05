package com.example.hywm.service;

import com.example.hywm.dto.DishDto;
import com.example.hywm.dto.SetmealDto;
import com.example.hywm.entity.Setmeal;
import com.example.hywm.entity.SetmealDish;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/12 16:14
 **/
public interface SetmealService {

    Boolean insertSetmeal(SetmealDto setmealDto) throws Exception;

    PageResult selectSetmealPage(PageReqVo pageReqVo) throws Exception;

    SetmealDto selectSetmealById(String id) throws Exception;

    Boolean editSetmeal(SetmealDto setmealDto) throws Exception;

    Boolean deleteSetmeal(String id) throws Exception;

    Boolean editSetmealStatus(String status,String id) throws Exception;

    List<Setmeal> selectSetmealByCategoryId(String id,String status) throws Exception;

    List<DishDto> selectDishById(String id) throws Exception;
}
