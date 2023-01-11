package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Interface.IRestaurantService;
import com.ingsw.frontend.Retrofit.RestaurantApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class RestaurantService implements IRestaurantService {

    private RestaurantApi restaurantApi;

    public RestaurantService() {
        this.restaurantApi = RetrofitService.getRetrofit().create(RestaurantApi.class);
    }

    @Override
    public void getByName(String name){}
}
