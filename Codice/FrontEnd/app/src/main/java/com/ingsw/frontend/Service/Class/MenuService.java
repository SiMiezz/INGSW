package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Retrofit.MenuApi;
import com.ingsw.frontend.Retrofit.RetrofitService;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Interface.IMenuService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MenuService implements IMenuService {

    private MenuApi menuApi;

    public MenuService() {
        this.menuApi = RetrofitService.getRetrofit().create(MenuApi.class);
    }

    @Override
    public void getByRestaurantName(Callback callback, String name){
        menuApi.getByRestaurantName(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Menu>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull Menu menu) {
                        callback.returnResult(menu);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }
}
