package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Enumerations.User_Type;
import com.ingsw.backend.Model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User create(User user);

    Boolean deleteByEmail(String email);

    List<User> getByRestaurantName(String name);

    Optional<User> getByEmailAndPassword(String email,String pwd);

    List<User> getByRestaurantNameAndJob(String restaurant_name, User_Type job);
}
