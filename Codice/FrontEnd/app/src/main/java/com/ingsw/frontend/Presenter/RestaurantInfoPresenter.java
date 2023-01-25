package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.RestaurantService;
import com.ingsw.frontend.View.Fragment.RestaurantInfoFragment;

public class RestaurantInfoPresenter {

    RestaurantInfoFragment restaurantInfoFragment;
    RestaurantService restaurantService;


    public RestaurantInfoPresenter(RestaurantInfoFragment restaurantInfoFragment){
        this.restaurantInfoFragment = restaurantInfoFragment;
        restaurantService = new RestaurantService();
    }


    public void getByName(String name){
        restaurantService.getByName(new Callback() {
            @Override
            public void returnResult(Object o) {

                Restaurant restaurant = (Restaurant) o;
                restaurantInfoFragment.loadInfo(restaurant);

            }

            @Override
            public void returnError(Throwable e) {

            }
        }, name);
    }


}
