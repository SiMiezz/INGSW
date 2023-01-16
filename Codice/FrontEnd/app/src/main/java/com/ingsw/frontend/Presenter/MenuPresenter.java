package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.MenuService;
import com.ingsw.frontend.View.Fragment.LoginFragment;
import com.ingsw.frontend.View.Fragment.MenuFragment;

public class MenuPresenter {

    private MenuFragment menuFragment;
    private MenuService menuService;

    private LoginFragment loginFragment;

    // CONSTRUCTOR

    public MenuPresenter(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
        menuService = new MenuService();
    }

    // GETTER AND SETTER

    public MenuFragment getMenuFragment() {
        return menuFragment;
    }

    public void getByRestaurantName(String name){
        menuService.getByRestaurantName(new Callback() {
            @Override
            public void returnResult(Object o) {
                Menu menu = (Menu) o;

                loginFragment.loadMenu(menu);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },name);
    }
}
