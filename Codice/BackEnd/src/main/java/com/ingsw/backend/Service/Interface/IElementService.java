package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Element;

import java.util.List;

public interface IElementService {

    Element create(Element element);

    Boolean deleteById(Integer id);

    List<Element> getByCategoryId(Integer id);

    List<Element> getByCategoryIdAndRestaurantId(String restaurantName, Integer idCategory);
}
