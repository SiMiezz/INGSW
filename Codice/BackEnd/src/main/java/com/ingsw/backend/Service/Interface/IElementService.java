package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Element;

import java.util.List;

public interface IElementService {

    void create(Element element);

    void delete(Element element);

    List<Element> getByCategoryId(Integer id);
}
