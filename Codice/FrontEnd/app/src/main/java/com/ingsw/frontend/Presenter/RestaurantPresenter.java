package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.RestaurantService;
import com.ingsw.frontend.View.Activity.HomeActivity;
import com.ingsw.frontend.View.Fragment.LoginFragment;
import com.ingsw.frontend.View.Fragment.RestaurantFragment;
import com.ingsw.frontend.View.Fragment.SectionButtonsFragment;

public class RestaurantPresenter {

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

    // GETTER AND SETTER

    public RestaurantFragment getRestaurantFragment() {
        return restaurantFragment;
    }

    public void getByName(String name){
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
}
