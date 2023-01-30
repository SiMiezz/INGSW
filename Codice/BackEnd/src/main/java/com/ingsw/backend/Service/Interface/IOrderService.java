package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Order;

import java.util.List;

public interface IOrderService {

    void create(Order order);

    public List<Order> getByTablerestaurantId(Integer id);

    void delete(Order order);

}
