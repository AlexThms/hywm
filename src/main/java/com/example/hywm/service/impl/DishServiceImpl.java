package com.example.hywm.service.impl;

import com.example.hywm.entity.Dish;
import com.example.hywm.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/1/15 19:53
 **/
@Service
public class DishServiceImpl implements DishService {
    @Override
    public List<Dish> selectDishPage() throws Exception {
        return null;
    }

    @Override
    public Boolean insertDish(Dish dish) throws Exception {
        return null;
    }

    @Override
    public Boolean editDish(Dish dish) throws Exception {
        return null;
    }

    @Override
    public Boolean deleteDish(String id) throws Exception {
        return null;
    }

    @Override
    public Dish selectDishById(String id) throws Exception {
        return null;
    }
}
