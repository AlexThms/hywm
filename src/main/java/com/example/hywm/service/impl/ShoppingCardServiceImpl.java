package com.example.hywm.service.impl;

import com.example.hywm.entity.SetmealDish;
import com.example.hywm.entity.ShoppingCard;
import com.example.hywm.mapper.ShoppingCardMapper;
import com.example.hywm.service.ShoppingCardService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/25 14:52
 **/
@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {
    @Autowired
    ShoppingCardMapper shoppingCardMapper;

    @Override
    public List<ShoppingCard> queryShoppingCard(String id) throws Exception {
        List<ShoppingCard> shoppingCardList = shoppingCardMapper.selectShoppingCardById(id);
        return shoppingCardList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertShoppingCard(ShoppingCard shoppingCard) throws Exception {
        List<ShoppingCard> cardList = new ArrayList<>();
        ShoppingCard card = null;
        Integer integer = 0;
        if(StringUtils.isNotEmpty(shoppingCard.getDishId())){
            cardList = shoppingCardMapper.selectByDishId(shoppingCard.getDishId());
            if(!CollectionUtils.isEmpty(cardList)){
                List<ShoppingCard> collect = cardList.stream().filter(o -> o.getDishFlavor().equals(shoppingCard.getDishFlavor())).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(collect)){
                    card = collect.get(0);
                }
            }
        }else if(StringUtils.isNotEmpty(shoppingCard.getSetmealId())){
            card = shoppingCardMapper.selectBySetmealId(shoppingCard.getSetmealId());
        }
        if(card != null){
            card.setNumber(card.getNumber() + 1);
            integer = shoppingCardMapper.editShoppingCard(card);
        }else {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            shoppingCard.setId(uuid);
            shoppingCard.setNumber(1);
            shoppingCard.setCreateTime(LocalDateTime.now());
            integer = shoppingCardMapper.insertShoppingCard(shoppingCard);
        }
        if(integer != 1){
            return false;
        }
        return true;
    }

    @Override
    public Boolean editShoppingCard(ShoppingCard shoppingCard,String userId) throws Exception {
        List<ShoppingCard> cardList = new ArrayList<>();
        ShoppingCard cart = new ShoppingCard();
        Integer integer = 0;
        if(StringUtils.isNotEmpty(shoppingCard.getDishId())){
            cardList = shoppingCardMapper.selectByDishId(shoppingCard.getDishId());
            List<ShoppingCard> collect = cardList.stream().filter(o -> o.getDishFlavor().equals(shoppingCard.getDishFlavor())).collect(Collectors.toList());
            cart = collect.get(0);
        }else {
            cart = shoppingCardMapper.selectBySetmealId(shoppingCard.getSetmealId());
        }
        int number = cart.getNumber() - 1;
        if(number > 0){
            cart.setNumber(number);
            integer = shoppingCardMapper.editShoppingCard(cart);
        }else {
            integer = shoppingCardMapper.deleteShoppingCard(cart.getId());
        }
        if( integer != 1){
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteShoppingCard(String userId) throws Exception {
        List<ShoppingCard> cardList = shoppingCardMapper.selectShoppingCardById(userId);
        Integer integer = shoppingCardMapper.deleteAllShoppingCard(userId);
        if(integer != cardList.size()){
            return false;
        }
        return true;
    }
}
