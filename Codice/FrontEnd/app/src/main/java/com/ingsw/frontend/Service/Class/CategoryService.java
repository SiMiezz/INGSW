package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Interface.ICategoryService;
import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Retrofit.CategoryApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

import java.util.List;

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
    public void create(Callback callback, Category category){
        categoryApi.create(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Void unused) {
                        callback.returnResult(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void deleteById(Callback callback, Integer id){
        categoryApi.deleteById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Void unused) {
                        callback.returnResult(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void getByMenuQrCode(Callback callback, String code){
        categoryApi.getByMenuQrCode(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Category>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull List<Category> categories) {
                        callback.returnResult(categories);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
