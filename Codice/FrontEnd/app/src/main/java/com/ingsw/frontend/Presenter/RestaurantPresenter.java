package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.RestaurantService;
import com.ingsw.frontend.View.Fragment.RestaurantFragment;

public class RestaurantPresenter {

    private RestaurantFragment restaurantFragment;
    private RestaurantService restaurantService;

    // CONSTRUCTOR

    public RestaurantPresenter(RestaurantFragment restaurantFragment) {
        this.restaurantFragment = restaurantFragment;
        restaurantService = new RestaurantService();
    }

    // GETTER AND SETTER

    public RestaurantFragment getRestaurantFragment() {
        return restaurantFragment;
    }

    public void getByName(String name){
        restaurantService.getByName(new Callback() {
            @Override
            public void returnResult(Object o) {
                Restaurant restaurant = (Restaurant) o;

                restaurantFragment.loadRestaurant(restaurant);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },name);
    }
}
