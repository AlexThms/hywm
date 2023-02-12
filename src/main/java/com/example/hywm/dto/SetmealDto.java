package com.example.hywm.dto;

import com.example.hywm.entity.Setmeal;
import com.example.hywm.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/12 16:48
 **/
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
