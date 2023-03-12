package com.example.hywm.controller;

import com.example.hywm.common.Result;
import com.example.hywm.common.WMContonst;
import com.example.hywm.entity.Category;
import com.example.hywm.entity.ShoppingCard;
import com.example.hywm.service.ShoppingCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/25 14:51
 **/
@RestController
@RequestMapping("/shoppingCard")
@Slf4j
public class ShoppingCardController {

    @Autowired
    ShoppingCardService shoppingCardService;

    @GetMapping("/list")
    public Result queryShoppingCard(HttpServletRequest req) {
        try {
            String id = (String) req.getSession().getAttribute("User");
            List<ShoppingCard> shoppingCardList = shoppingCardService.queryShoppingCard(id);
            log.info("查询返回：{}", shoppingCardList);
            return Result.success(shoppingCardList);
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_QUERY.getMsg());
        }
    }

    @PostMapping("/add")
    public Result insertShoppingCard(HttpServletRequest req, @RequestBody ShoppingCard shoppingCard) {
        try {
                String id = (String) req.getSession().getAttribute("User");
                shoppingCard.setUserId(id);
            Boolean bool = shoppingCardService.insertShoppingCard(shoppingCard);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_INSERT.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_INSERT.getMsg());
        }
    }

    @PostMapping("/sub")
    public Result editShoppingCard(HttpServletRequest req, @RequestBody ShoppingCard shoppingCard) {
        try {
            String id = (String) req.getSession().getAttribute("User");
            Boolean bool = shoppingCardService.editShoppingCard(shoppingCard,id);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_UPDATE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_UPDATE.getMsg());
        }
    }

    @DeleteMapping("/clean")
    public Result deleteShoppingCard(HttpServletRequest req) {
        try {
            String id = (String)req.getSession().getAttribute("User");
            Boolean bool = shoppingCardService.deleteShoppingCard(id);
            return bool ? Result.success(WMContonst.SuccessEnum.Success_DELETE.getMsg()) : Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        } catch (Exception exception) {
            return Result.error(WMContonst.ErrorEnum.Error_DELETE.getMsg());
        }
    }
}
