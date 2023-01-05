package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Model.TableRestaurant;

public interface ITableRestaurantService {

    public Iterable<TableRestaurant> getByRestaurant(Restaurant restaurant);
}
