package com.example.hywm.service;

import com.example.hywm.entity.Dish;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 19:52
 **/
public interface DishService {
    List<Dish> selectDishPage() throws Exception;

    Boolean insertDish(Dish dish) throws Exception;

    Boolean editDish(Dish dish) throws Exception;

    Boolean deleteDish(String id) throws Exception;

    Dish selectDishById(String id) throws Exception;

}
