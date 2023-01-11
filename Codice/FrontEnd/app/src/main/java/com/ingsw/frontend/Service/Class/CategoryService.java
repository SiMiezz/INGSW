package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.Service.Interface.ICategoryService;
import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Retrofit.CategoryApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryService implements ICategoryService {

    private CategoryApi categoryApi;

    public CategoryService() {
        this.categoryApi = RetrofitService.getRetrofit().create(CategoryApi.class);
    }

    @Override
    public void create(Category category){}

    @Override
    public void deleteById(Integer id){}

    @Override
    public void getByMenuQrCode(String code){}
}
