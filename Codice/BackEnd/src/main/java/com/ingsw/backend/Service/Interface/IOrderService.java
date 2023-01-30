package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Order;

import java.util.List;

public interface IOrderService {

    void create(Order order);

    List<Order> getByTablerestaurantId(Integer id);

    Double sumPriceByTableId(Integer id);

    void delete(Order order);

}
