package com.example.hywm.mapper;

import com.example.hywm.entity.OrderDetail;
import com.example.hywm.entity.Orders;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/2/18 12:30
 **/
@Repository
public interface OrdersMapper {

    Integer insertOrder(Orders orders);

    Integer insertOrderDetail(List<OrderDetail> list);

    List<Orders> selectOrdersPage();

    List<Orders> selectOrdersPageById(String userId);

    List<OrderDetail> selectOrderDetail(String id);

    Integer editOrderStatus(String status);

    int selectNumber(LocalDate dateTime);
}
