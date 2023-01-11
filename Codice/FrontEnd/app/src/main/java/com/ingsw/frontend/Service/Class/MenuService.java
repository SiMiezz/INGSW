package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Interface.IMenuService;
import com.ingsw.frontend.Retrofit.MenuApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class MenuService implements IMenuService {

    private MenuApi menuApi;

    public MenuService() {
        this.menuApi = RetrofitService.getRetrofit().create(MenuApi.class);
    }

    @Override
    public void getByRestaurantName(String name){}
}
