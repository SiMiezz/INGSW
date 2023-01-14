package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Service.Callback;

public interface IElementService {

    void create(Callback callback, Element element);

    void deleteById(Callback callback, Integer id);

    void getByCategoryId(Callback callback, Integer id);

    void getByCategoryAndRestaurantId(Callback callback, String restaurantName, Integer idCategory);
}
