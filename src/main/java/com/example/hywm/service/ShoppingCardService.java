package com.example.hywm.service;

import com.example.hywm.entity.ShoppingCard;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/25 14:52
 **/
public interface ShoppingCardService {

    List<ShoppingCard> queryShoppingCard() throws Exception;

    Boolean insertShoppingCard(ShoppingCard shoppingCard) throws Exception;

    Boolean editShoppingCard(ShoppingCard shoppingCard,String id) throws Exception;

    Boolean deleteShoppingCard(String id) throws Exception;
}
