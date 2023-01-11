package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Interface.ITableRestaurantService;
import com.ingsw.frontend.Retrofit.RetrofitService;
import com.ingsw.frontend.Retrofit.TableRestaurantApi;

public class TableRestaurantService implements ITableRestaurantService {

    private TableRestaurantApi tableRestaurantApi;

    public TableRestaurantService() {
        this.tableRestaurantApi = RetrofitService.getRetrofit().create(TableRestaurantApi.class);
    }

    @Override
    public void getByRestaurantName(String name){}
}
