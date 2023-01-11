package com.ingsw.frontend.DAO.Interface;

import com.ingsw.frontend.Model.Order;

public interface IOrderDAO {

    void create(Order order);

    void deleteById(Integer id);

    void getByTableRestaurantId(Integer id);
}
