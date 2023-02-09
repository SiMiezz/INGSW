package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Service.Callback;

import java.sql.Date;

public interface IOrderService {

    void create(Callback callback,  Order order);

    void deleteById(Callback callback, Integer id);

    void getByTableRestaurantId(Callback callback, Integer id);

    void sumPriceByTableId(Callback callback, Integer id);

    void delete(Callback callback, Order order);

    void getCountElementOrderedStats(Callback callback, Integer id, Date fromDate, Date toDate);
}
