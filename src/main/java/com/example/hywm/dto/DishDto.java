package com.example.hywm.dto;

import com.example.hywm.entity.Dish;
import com.example.hywm.entity.DishFlavor;
import lombok.Data;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/9 22:08
 **/
@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors ;

    private String categoryName;

    private Integer copies;
}
