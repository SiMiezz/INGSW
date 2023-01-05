package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Element;

public interface IElementService {

    public Element create(Element element);

    public Boolean deleteById(int id);
}
