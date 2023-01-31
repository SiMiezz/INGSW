package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.RestaurantService;
import com.ingsw.frontend.View.Fragment.LoginFragment;
import com.ingsw.frontend.View.Fragment.RestaurantFragment;
import com.ingsw.frontend.View.Fragment.RestaurantInfoFragment;

public class RestaurantPresenter {

    RestaurantInfoFragment restaurantInfoFragment;
    private RestaurantFragment restaurantFragment;
    private RestaurantService restaurantService;

    private LoginFragment loginFragment;
    private MenuPresenter menuPresenter;

    // CONSTRUCTOR

    public RestaurantPresenter(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
        menuPresenter = new MenuPresenter(loginFragment);
        restaurantService = new RestaurantService();
    }

    public RestaurantPresenter(RestaurantInfoFragment restaurantInfoFragment){
        this.restaurantInfoFragment = restaurantInfoFragment;
        restaurantService = new RestaurantService();
    }

    // GETTER AND SETTER

    public RestaurantFragment getRestaurantFragment() {
        return restaurantFragment;
    }

    public void getByNameIntent(String name){
        restaurantService.getByName(new Callback() {
            @Override
            public void returnResult(Object o) {
                Restaurant restaurant = (Restaurant) o;

                loginFragment.loadRestaurant(restaurant);
                menuPresenter.getByRestaurantName(restaurant.getName());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },name);
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
