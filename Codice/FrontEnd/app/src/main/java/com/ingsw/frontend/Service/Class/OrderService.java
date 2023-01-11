package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Interface.IOrderService;
import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Retrofit.OrderApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class OrderService implements IOrderService {

    private OrderApi orderRetrofit;

    public OrderService() {
        this.orderRetrofit = RetrofitService.getRetrofit().create(OrderApi.class);
    }

    @Override
    public void create(Order order){}

    @Override
    public void deleteById(Integer id){}

    @Override
    public void getByTableRestaurantId(Integer id){}
}
