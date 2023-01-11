package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Order;

public interface IOrderService {

    void create(Order order);

    void deleteById(Integer id);

    void getByTableRestaurantId(Integer id);
}
