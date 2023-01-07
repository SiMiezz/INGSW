package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Menu;

import java.util.Optional;

public interface IMenuService {

    public Optional<Menu> getByRestaurantName(String name);
}
