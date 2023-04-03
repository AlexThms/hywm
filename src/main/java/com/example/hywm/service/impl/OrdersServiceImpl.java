package com.example.hywm.service.impl;

import com.example.hywm.common.PageUtils;
import com.example.hywm.common.ValidateCodeUtils;
import com.example.hywm.dto.OrderDto;
import com.example.hywm.entity.AddressBook;
import com.example.hywm.entity.OrderDetail;
import com.example.hywm.entity.Orders;
import com.example.hywm.entity.ShoppingCard;
import com.example.hywm.mapper.AddressBookMapper;
import com.example.hywm.mapper.OrdersMapper;
import com.example.hywm.mapper.ShoppingCardMapper;
import com.example.hywm.mapper.UserMapper;
import com.example.hywm.service.AddressBookService;
import com.example.hywm.service.OrdersService;
import com.example.hywm.vo.OrderVo;
import com.example.hywm.vo.PageReqVo;
import com.example.hywm.vo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/18 12:30
 **/
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    ShoppingCardMapper shoppingCardMapper;

    @Autowired
    AddressBookMapper addressBookMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean insertOrder(OrderVo orderVo, String userId) throws Exception {
        BigDecimal amount = new BigDecimal(0);
        Orders orders = new Orders();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<ShoppingCard> cardList = shoppingCardMapper.selectShoppingCardById(userId);
        for (ShoppingCard card:cardList
             ) {
            BigDecimal num = new BigDecimal(card.getNumber());
            amount = amount.add(card.getAmount().multiply(num));
        }
        String number = String.valueOf(System.currentTimeMillis()) + String.valueOf(ValidateCodeUtils.generateValidateCode(4));
        AddressBook addressBook = addressBookMapper.selectAddressOne(orderVo.getAddressBookId());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        BeanUtils.copyProperties(orderVo,orders);
        BeanUtils.copyProperties(addressBook,orders);
        orders.setUserName(userMapper.selectById(userId));
        orders.setAddress(addressBook.getDetail());
        orders.setId(uuid);
        orders.setNumber(number);
        orders.setAmount(amount);
        orders.setOrderTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setUserId(userId);
        orders.setType(Integer.parseInt(orderVo.getType()));
        Integer ordersNum = ordersMapper.insertOrder(orders);
        cardList.forEach(obj->{
            OrderDetail detail = new OrderDetail();
            BeanUtils.copyProperties(obj,detail);
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            detail.setId(id);
            detail.setOrderId(uuid);
            orderDetailList.add(detail);
        });
        Integer integer = ordersMapper.insertOrderDetail(orderDetailList);
        shoppingCardMapper.deleteAllShoppingCard(userId);
        if(integer != orderDetailList.size() || ordersNum != 1){
            return false;
        }
        return true;
    }

    @Override
    public PageResult selectOrdersPage(PageReqVo pageReqVo, String number, LocalDateTime beginTime, LocalDateTime endTime) throws Exception {
        int page = pageReqVo.getPage();
        int pageSize = pageReqVo.getPageSize();
        Page<Object> pageHelper = PageHelper.startPage(page, pageSize);
        List<Orders> ordersList = ordersMapper.selectOrdersPage();
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
        PageResult pageResult = PageUtils.getPageResult(pageInfo, pageHelper);
        return pageResult;
    }

    @Override
    public Boolean editOrderStatus(String status) throws Exception {
        Integer integer = ordersMapper.editOrderStatus(status);
        if(integer != 1){
            return false;
        }
        return true;
    }

    @Override
    public List<OrderDetail>  selectOrderDetail(String id) throws Exception {
        List<OrderDetail> list = ordersMapper.selectOrderDetail(id);
        return list;
    }

    @Override
    public String getNumber() throws Exception {
        int number = ordersMapper.selectNumber(LocalDate.now());
        return String.valueOf(number+1);
    }

    @Override
    public PageResult selectOrderPage(PageReqVo pageReqVo, String id) throws Exception {
        List<OrderDto> list = new ArrayList<>();
        Page<Object> pageHelp = PageHelper.startPage(pageReqVo.getPage(), pageReqVo.getPageSize());
        List<Orders> orders = ordersMapper.selectOrdersPageById(id);
        orders.forEach(obj->{
            OrderDto dto = new OrderDto();
            BeanUtils.copyProperties(obj,dto);
            dto.setOrderDetails(ordersMapper.selectOrderDetail(obj.getId()));
            list.add(dto);
        });
        PageInfo<OrderDto> pageInfo = new PageInfo<>(list);
        return PageUtils.getPageResult(pageInfo,pageHelp);
    }
}
