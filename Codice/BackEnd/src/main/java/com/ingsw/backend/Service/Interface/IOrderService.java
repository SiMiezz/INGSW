package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Order;

import java.util.List;

public interface IOrderService {

    Order create(Order order);

    Boolean deleteById(Integer id);

    //public List<Order> getByTablerestaurantId(Integer id);
}
