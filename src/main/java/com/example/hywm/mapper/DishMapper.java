package com.example.hywm.mapper;

import com.example.hywm.entity.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 19:53
 **/
@Repository
public interface DishMapper {

    List<Dish> selectAllDish();

    Dish selectDishById();

    Integer insertDish();

    Integer editDish();

    Integer deleteDish();
}
