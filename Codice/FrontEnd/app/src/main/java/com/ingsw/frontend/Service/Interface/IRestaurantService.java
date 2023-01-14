package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Service.Callback;

public interface IRestaurantService {

    void getByName(Callback callback, String name);
}
