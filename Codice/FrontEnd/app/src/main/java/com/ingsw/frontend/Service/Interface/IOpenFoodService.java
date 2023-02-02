package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Service.Callback;

public interface IOpenFoodService {

    void getProductList(Callback callback, String name);
}
