package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Service.Callback;

public interface IUserService {

    void create(Callback callback, User user);

    void deleteByEmail(Callback callback, String email);

    void getByRestaurantName(Callback callback, String name);

    void getByEmailAndPassword(Callback callback, String email, String pwd);
}
