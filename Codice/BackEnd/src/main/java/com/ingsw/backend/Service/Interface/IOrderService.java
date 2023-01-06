package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Order;

public interface IOrderService {

    public Order create(Order order);

    public boolean deleteById(Integer id);
}
