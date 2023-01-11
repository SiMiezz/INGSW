package com.ingsw.frontend.DAO.Class;

import com.ingsw.frontend.DAO.Interface.ICategoryDAO;
import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Retrofit.AllergenRetrofit;
import com.ingsw.frontend.Retrofit.CategoryRetrofit;
import com.ingsw.frontend.Retrofit.RetrofitService;

public class CategoryDAO implements ICategoryDAO {

    private CategoryRetrofit categoryRetrofit;

    public CategoryDAO() {
        this.categoryRetrofit = RetrofitService.getRetrofit().create(CategoryRetrofit.class);
    }

    @Override
    public void create(Category category){}

    @Override
    public void deleteById(Integer id){}

    @Override
    public void getByMenuQrCode(String code){}
}
