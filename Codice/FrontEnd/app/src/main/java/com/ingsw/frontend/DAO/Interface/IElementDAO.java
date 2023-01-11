package com.ingsw.frontend.DAO.Interface;

import com.ingsw.frontend.Model.Element;

public interface IElementDAO {

    void create(Element element);

    void deleteById(Integer id);

    void getByCategoryId(Integer id);
}
