package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Interface.IRestaurantService;
import com.ingsw.frontend.Retrofit.RestaurantApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RestaurantService implements IRestaurantService {

    private RestaurantApi restaurantApi;

    public RestaurantService() {
        this.restaurantApi = RetrofitService.getRetrofit().create(RestaurantApi.class);
    }

    @Override
    public void getByName(Callback callback, String name){
        restaurantApi.getByName(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Restaurant>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull Restaurant restaurant) {
                        callback.returnResult(restaurant);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }
}
