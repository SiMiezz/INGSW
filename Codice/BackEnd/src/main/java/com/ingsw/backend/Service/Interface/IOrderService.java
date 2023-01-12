package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Order;

import java.util.List;

public interface IOrderService {

    public Order create(Order order);

    public boolean deleteById(Integer id);

    //public List<Order> getByTablerestaurantId(Integer id);
}
