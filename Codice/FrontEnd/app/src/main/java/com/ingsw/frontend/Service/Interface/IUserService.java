package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.User;

public interface IUserService {

    void create(User user);

    void deleteById(String id);

    void getByRestaurantName(String name);

    void getById(String id);
}
