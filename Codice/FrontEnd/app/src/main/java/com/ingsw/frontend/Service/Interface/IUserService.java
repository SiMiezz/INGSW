package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Service.Callback;

public interface IUserService {

    void update(Callback callback, User user);

    void create(Callback callback, User user);

    void delete(Callback callback, User user);

    void getByRestaurantName(Callback callback, String name);

    void checkUser(Callback callback, String email, String pwd);

    void getUser(Callback callback, String email, String pwd);

    void getByRestaurantNameAndJob(Callback callback, String restaurant_name, User_Type job);
}
