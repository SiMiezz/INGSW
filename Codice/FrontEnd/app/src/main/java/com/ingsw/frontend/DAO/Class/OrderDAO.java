package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.IOrderDAO;
import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Retrofit.MenuRetrofit;
import com.ingsw.frontend.Retrofit.OrderRetrofit;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class OrderDAO implements IOrderDAO {

    private OrderRetrofit orderRetrofit;

    public OrderDAO() {
        this.orderRetrofit = RetrofitService.getRetrofit().create(OrderRetrofit.class);
    }

    @Override
    public void create(Order order){}

    @Override
    public void deleteById(Integer id){}

    @Override
    public void getByTableRestaurantId(Integer id){}
}
