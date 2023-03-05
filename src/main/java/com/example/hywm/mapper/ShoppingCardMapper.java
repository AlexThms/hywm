package com.example.hywm.mapper;

import com.example.hywm.entity.ShoppingCard;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/25 14:54
 **/
@Repository
public interface ShoppingCardMapper {

    List<ShoppingCard> selectShoppingCard();

    List<ShoppingCard> selectShoppingCardById(String userId);

    List<ShoppingCard> selectByDishId(String dishId);

    ShoppingCard selectBySetmealId(String setmealId);

    Integer insertShoppingCard(ShoppingCard shoppingCard);

    Integer editShoppingCard(ShoppingCard shoppingCard);

    Integer deleteAllShoppingCard(String userId);

    Integer deleteShoppingCard(String id);

}
