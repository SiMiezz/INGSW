package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Element;

import java.util.List;

public interface IElementService {

    public Element create(Element element);

    public Boolean deleteById(Integer id);

    public List<Element> getByCategoryId(Integer id);
}
