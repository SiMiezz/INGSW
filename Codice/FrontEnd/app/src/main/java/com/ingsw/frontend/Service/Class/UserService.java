package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Interface.IUserService;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Retrofit.RetrofitService;
import com.ingsw.frontend.Retrofit.UserApi;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserService implements IUserService {

    private UserApi userApi;

    public UserService() {
        this.userApi = RetrofitService.getRetrofit().create(UserApi.class);
    }

    @Override
    public void create(Callback callback, User user){
        userApi.create(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Void>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        callback.returnResult(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }

    @Override
    public void deleteByEmail(Callback callback, String email){
        userApi.deleteByEmail(email)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Void>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        callback.returnResult(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }

    @Override
    public void getByRestaurantName(Callback callback, String name){
        userApi.getByRestaurantName(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull List<User> users) {
                        callback.returnResult(users);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }

    @Override
    public void checkUser(Callback callback, String email, String pwd){
        userApi.getByEmailAndPassword(email,pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull User user) {
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
