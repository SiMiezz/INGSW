package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Interface.ITableRestaurantService;
import com.ingsw.frontend.Retrofit.RetrofitService;
import com.ingsw.frontend.Retrofit.TableRestaurantApi;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TableRestaurantService implements ITableRestaurantService {

    private TableRestaurantApi tableRestaurantApi;

    public TableRestaurantService() {
        this.tableRestaurantApi = RetrofitService.getRetrofit().create(TableRestaurantApi.class);
    }

    @Override
    public void getByRestaurantName(Callback callback, String name){
        tableRestaurantApi.getByRestaurantName(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<TableRestaurant>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull List<TableRestaurant> tableRestaurantList) {
                        callback.returnResult(tableRestaurantList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }
}
