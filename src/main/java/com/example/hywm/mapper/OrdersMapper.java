package com.example.hywm.mapper;

import com.example.hywm.entity.OrderDetail;
import com.example.hywm.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    List<Orders> selectOrdersPage(@Param("number")String number,@Param("beginTime") LocalDateTime beginTime,@Param("endTime") LocalDateTime endTime);

    List<Orders> selectOrdersPageById(String userId);

    List<OrderDetail> selectOrderDetail(String id);

    Integer editOrderStatus(@Param("status") int status, @Param("id")String id);

    int selectNumber(LocalDate dateTime);
}
