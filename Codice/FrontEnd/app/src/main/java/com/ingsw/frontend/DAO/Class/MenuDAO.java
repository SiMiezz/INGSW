package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.IMenuDAO;
import com.ingsw.frontend.Retrofit.ElementRetrofit;
import com.ingsw.frontend.Retrofit.MenuRetrofit;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class MenuDAO implements IMenuDAO {

    private MenuRetrofit menuRetrofit;

    public MenuDAO() {
        this.menuRetrofit = RetrofitService.getRetrofit().create(MenuRetrofit.class);
    }

    @Override
    public void getByRestaurantName(String name){}
}
