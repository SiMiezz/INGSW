package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Element;

public interface IElementService {

    void create(Element element);

    void deleteById(Integer id);

    void getByCategoryId(Integer id);
}
