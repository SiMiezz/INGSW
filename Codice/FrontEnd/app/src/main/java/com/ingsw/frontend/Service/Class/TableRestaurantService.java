package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Retrofit.RetrofitService;
import com.ingsw.frontend.Retrofit.TableRestaurantApi;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Interface.ITableRestaurantService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TableRestaurantService implements ITableRestaurantService {

    private TableRestaurantApi tableRestaurantApi;

    public TableRestaurantService() {
        this.tableRestaurantApi = RetrofitService.getRetrofit().create(TableRestaurantApi.class);
    }

    @Override
    public void update(Callback callback, TableRestaurant tableRestaurant) {
        tableRestaurantApi.update(tableRestaurant)
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

    public void countTotalByRestaurantName(Callback callback, String name) {
        tableRestaurantApi.countTotalByRestaurantName(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull Integer total) {
                        callback.returnResult(total);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }

    public void countByRestaurantNameAndFree(Callback callback, String name, boolean free) {
        tableRestaurantApi.countByRestaurantNameAndFree(name,free)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull Integer total) {
                        callback.returnResult(total);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }

    public void getById(Callback callback, Integer id) {
        tableRestaurantApi.getById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TableRestaurant>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull TableRestaurant tableRestaurant) {
                        callback.returnResult(tableRestaurant);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }
}
