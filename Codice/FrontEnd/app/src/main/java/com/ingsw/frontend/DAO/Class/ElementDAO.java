package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.IElementDAO;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Retrofit.ClientRetrofit;
import com.ingsw.frontend.Retrofit.ElementRetrofit;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class ElementDAO implements IElementDAO {

    private ElementRetrofit elementRetrofit;

    public ElementDAO() {
        this.elementRetrofit = RetrofitService.getRetrofit().create(ElementRetrofit.class);
    }

    @Override
    public void create(Element element){}

    @Override
    public void deleteById(Integer id){}

    @Override
    public void getByCategoryId(Integer id){}
}
