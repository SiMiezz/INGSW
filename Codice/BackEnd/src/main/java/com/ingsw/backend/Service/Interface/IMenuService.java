package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Menu;
import com.ingsw.backend.Model.Restaurant;

import java.util.Optional;

public interface IMenuService {

    public Optional<Menu> getByRestaurantName(String name);
}
