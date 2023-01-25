package com.ingsw.frontend.Service.Interface;

import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Service.Callback;

public interface IElementService {

    void create(Callback callback, Element element);

    void delete(Callback callback, Element element);

    void getByCategoryId(Callback callback, Integer id);
}
