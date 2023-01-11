package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.IAllergenDAO;
import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.Retrofit.AllergenRetrofit;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class AllergenDAO implements IAllergenDAO {

    private AllergenRetrofit allergenRetrofit;

    public AllergenDAO() {
        this.allergenRetrofit = RetrofitService.getRetrofit().create(AllergenRetrofit.class);
    }

    @Override
    public void create(Allergen allergen){}

    @Override
    public void getAll(){}
}
