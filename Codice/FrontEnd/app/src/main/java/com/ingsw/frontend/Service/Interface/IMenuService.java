package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Service.Callback;

public interface IMenuService {

    void getByRestaurantName(Callback callback, String name);
}
