package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Enumerations.User_Type;
import com.ingsw.backend.Model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void update(User user);

    void create(User user);

    void delete(User user);

    List<User> getByRestaurantName(String name);

    Optional<User> getByEmailAndPassword(String email,String pwd);

    List<User> getByRestaurantNameAndJob(String restaurant_name, User_Type job);
}
