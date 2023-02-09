package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Order;

import java.sql.Date;
import java.util.List;

public interface IOrderService {

    void create(Order order);

    List<Order> getByTablerestaurantId(Integer id);

    Double sumPriceByTableId(Integer id);

    Long countByTableId(Integer id);

    void delete(Order order);

    Integer getCountElementOrdered(Integer id, Date from, java.sql.Date to);

}
