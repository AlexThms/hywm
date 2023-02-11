package com.example.hywm.mapper;

import com.example.hywm.dto.DishDto;
import com.example.hywm.entity.Dish;
import com.example.hywm.entity.DishFlavor;
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

    List<DishDto> selectAllDish();

    List<DishDto> selectDishLikeName(String name);

    Dish selectDishById(String id);

    List<DishFlavor> selectDishFlavor(String id);

    Integer insertDish(DishDto dishDto);

    Integer insertDishFlavor(List<DishFlavor> dishFlavor);

    Integer editDish(DishDto dishDto);

    Integer editDishFlavor(List<DishFlavor> dishFlavor);

    Integer editDishStatusQS(List<String> id);

    Integer editDishStatusTS(List<String> id);

    Integer deleteDish(List<String> id);

    Integer deleteDishFlavor(List<String> id);
}
