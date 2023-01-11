package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.ITableRestaurantDAO;
import com.ingsw.frontend.Retrofit.RestaurantRetrofit;
import com.ingsw.frontend.Retrofit.RetrofitService;
import com.ingsw.frontend.Retrofit.TableRestaurantRetrofit;

public class TableRestaurantDAO implements ITableRestaurantDAO {

    private TableRestaurantRetrofit tableRestaurantRetrofit;

    public TableRestaurantDAO() {
        this.tableRestaurantRetrofit = RetrofitService.getRetrofit().create(TableRestaurantRetrofit.class);
    }

    @Override
    public void getByRestaurantName(String name){}
}
