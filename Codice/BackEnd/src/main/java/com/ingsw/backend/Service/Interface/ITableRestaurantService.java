package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Model.TableRestaurant;

import java.util.List;
import java.util.Optional;

public interface ITableRestaurantService {

    List<TableRestaurant> getByRestaurantName(String name);

    Optional<TableRestaurant> getById(Integer id);

    Integer countTotalTableByRestaurantName(String name);
}
