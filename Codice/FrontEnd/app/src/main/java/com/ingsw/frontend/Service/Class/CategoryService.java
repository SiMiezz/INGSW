package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Interface.ICategoryService;
import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Retrofit.CategoryApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

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
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onComplete() {
                        callback.returnResult(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e);
                        callback.returnResult(false);
                    }
                });
    }

    @Override
    public void getByMenuId(Callback callback, Integer id){
        categoryApi.getByMenuId(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Category>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull List<Category> categoryList) {
                        callback.returnResult(categoryList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }

    public void getByMenuIdAndAliment(Callback callback, Integer id, Aliment_Type aliment_type) {
        categoryApi.getByMenuIdAndAliment(id, aliment_type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Category>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull List<Category> categoryList) {
                        callback.returnResult(categoryList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }

    public void deleteById(Callback callback, Integer id) {
        categoryApi.deleteById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onComplete() {
                        callback.returnResult(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e);
                        callback.returnResult(false);
                    }
                });
    }
}
