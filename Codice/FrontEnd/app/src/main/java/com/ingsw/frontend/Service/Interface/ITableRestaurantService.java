package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Service.Callback;

public interface ITableRestaurantService {

    void update(Callback callback, TableRestaurant tableRestaurant);

    void getByRestaurantName(Callback callback, String name);

    void countTotalByRestaurantName(Callback callback, String name);

    void countByRestaurantNameAndFree(Callback callback, String name, boolean free);

    void getById(Callback callback, Integer id);
}
