package com.example.hywm.mapper;

import com.example.hywm.dto.SetmealDto;
import com.example.hywm.entity.Setmeal;
import com.example.hywm.entity.SetmealDish;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/12 16:15
 **/
@Repository
public interface SetmealMapper {

    Integer insetSetmeal(Setmeal setmeal);

    Integer insetSetmealDish(List<SetmealDish> setmealDishList);

    List<SetmealDto> selectAllSetmeal();

    SetmealDto selectSetmealById(String id);

    List<SetmealDish> selectSetmealDishById(String id);

    List<Setmeal> selectSetmealByCategoryId(String id);

    List<SetmealDto> SelectSetmealLikeName(String name);

    Integer editSetmeal(Setmeal setmeal);

    Integer editSetmealDish(List<SetmealDish> setmealDish);

    Integer deleteSetmeal(List<String> id);

    Integer deleteSetmealDish(List<String> id);

    Integer deleteBySetmealId(List<String> id);

    Integer editDishStatusQS(List<String> idList);

    Integer editDishStatusTS(List<String> idList);
}
