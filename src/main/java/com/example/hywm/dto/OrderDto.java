package com.example.hywm.dto;

import com.example.hywm.entity.OrderDetail;
import com.example.hywm.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * Description todo
 * Author lihao
 *
 * @Date 2023/3/12 16:49
 **/
@Data
public class OrderDto extends Orders {

    private List<OrderDetail> orderDetails;
}
