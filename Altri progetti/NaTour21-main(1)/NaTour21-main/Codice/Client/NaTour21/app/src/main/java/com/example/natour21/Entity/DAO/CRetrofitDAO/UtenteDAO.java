package com.example.natour21.Entity.DAO.CRetrofitDAO;

import android.util.Log;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.rx.RxAmplify;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IUtenteDAO;
import com.example.natour21.Entity.Utente;
import com.example.natour21.Utils.Enumeration.API;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Request.Instance.UserRetrofit;
import com.example.natour21.Utils.Request.RequestGenerator;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class UtenteDAO implements IUtenteDAO {

    private UserRetrofit serviceUser;
    private final String TAG = "UtenteDAO";

    //Constructor
    public UtenteDAO() {
        this.serviceUser = RequestGenerator.retrofitInstance(API.USER_API)
                                            .create(UserRetrofit.class);
    }

    //Methods
    @Override
    public void listUser(Callback callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getUser(String username, Callback callback) {
        Log.i(TAG,"Calling DAO: getUser");
        serviceUser.getUser(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Utente>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Utente utente) {
                        Log.i(TAG, "onSuccess DAO: getUser started.");
                        callback.onSuccess(utente);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getUser started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getUserSocial(Callback callback) {
        Log.i(TAG,"Calling DAO: getUserSocial");
        RxAmplify.Auth.fetchUserAttributes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<AuthUserAttribute>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<AuthUserAttribute> authUserAttributes) {
                        Log.i("TAG", "onSuccess DAO: FetchAttributes started.");
                        Utente u = new Utente();
                        for(AuthUserAttribute ua : authUserAttributes) {
                            if(ua.getKey().getKeyString().equals("given_name"))
                                u.setNome(ua.getValue().toString());
                            else if(ua.getKey().getKeyString().equals("family_name"))
                                u.setCognome(ua.getValue().toString());
                            else if(ua.getKey().getKeyString().equals("email"))
                                u.setEmail(ua.getValue().toString());
                        }
                        u.setUsername(RxAmplify.Auth.getCurrentUser().getUsername().toString());
                        u.setPhotolnk("");
                        serviceUser.getUserSocial(u)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<Response<Void>>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {}
                                    @Override
                                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                                        Log.i(TAG, "onSuccess DAO: getUserSocial started.");
                                        callback.onSuccess(u);
                                    }
                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.e(TAG, "onError DAO: getUserSocial started.");
                                        Log.e(TAG, e.toString());
                                        callback.onFailure(e);
                                    }
                                });
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: FetchAttributes started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void postUser(Utente user, Callback callback) {
        Log.i(TAG,"Calling DAO: postUser");
        serviceUser.postUser(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: postUser started.");
                        if(voidResponse.code()==201)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: postUser started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void putUser(Utente user, Callback callback) {
        Log.i(TAG,"Calling DAO: putUser");
        serviceUser.putUser(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: putUser started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: putUser started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void deleteUser(String username, Callback callback) {
        Log.i(TAG,"Calling DAO: deleteUser");
        serviceUser.deleteUser(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: deleteUser started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: deleteUser started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    //Getter e Setter
    public UserRetrofit getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(UserRetrofit serviceUser) {
        this.serviceUser = serviceUser;
    }

}
