package com.ingsw.frontend.DAO.Interface;

import com.ingsw.frontend.Model.User;

public interface IUserDAO {

    void create(User user);

    void deleteById(String id);

    void getByRestaurantName(String name);

    void getById(String id);
}
