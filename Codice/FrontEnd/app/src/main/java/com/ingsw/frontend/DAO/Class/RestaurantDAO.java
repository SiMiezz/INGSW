package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.IRestaurantDAO;
import com.ingsw.frontend.Retrofit.OrderRetrofit;
import com.ingsw.frontend.Retrofit.RestaurantRetrofit;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class RestaurantDAO implements IRestaurantDAO {

    private RestaurantRetrofit restaurantRetrofit;

    public RestaurantDAO() {
        this.restaurantRetrofit = RetrofitService.getRetrofit().create(RestaurantRetrofit.class);
    }

    @Override
    public void getByName(String name){}
}
