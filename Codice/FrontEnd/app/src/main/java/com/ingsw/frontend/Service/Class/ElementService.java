package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Interface.IElementService;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Retrofit.ElementApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class ElementService implements IElementService {

    private ElementApi elementApi;

    public ElementService() {
        this.elementApi = RetrofitService.getRetrofit().create(ElementApi.class);
    }

    @Override
    public void create(Element element){}

    @Override
    public void deleteById(Integer id){}

    @Override
    public void getByCategoryId(Integer id){}
}
