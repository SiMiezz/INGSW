package com.example.natour21.Entity.DAO.CRetrofitDAO;

import android.util.Log;

import com.example.natour21.Entity.DAO.IRetrofitDAO.ITappaDAO;
import com.example.natour21.Entity.Tappa;
import com.example.natour21.Utils.Enumeration.API;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Request.Instance.TappaRetrofit;
import com.example.natour21.Utils.Request.RequestGenerator;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class TappaDAO implements ITappaDAO {

    private TappaRetrofit serviceTappa;
    private final String TAG = "TappaDAO";

    public TappaDAO(){
        this.serviceTappa = RequestGenerator.retrofitInstance(API.TAPPA_API)
                                                .create(TappaRetrofit.class);
    }

    //Methods
    @Override
    public void listTappe(Callback callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getTappaByID(Long idTappa, Callback callback) {
        Log.i(TAG,"Calling DAO: getTappaByID");
        serviceTappa.getTappaByID(idTappa)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Tappa>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Tappa tappa) {
                        Log.i(TAG, "onSuccess DAO: getTappaByID started.");
                        callback.onSuccess(tappa);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getTappaByID started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getTappaByItinerario(Long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: getTappaByItinerario");
        serviceTappa.getTappaByItinerario(idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Tappa>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<Tappa> tappe) {
                        Log.i(TAG, "onSuccess DAO: getTappaByItinerario started.");
                        callback.onSuccess(tappe);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getTappaByItinerario started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void createTappa(Tappa tappa, Callback callback) {
        Log.i(TAG,"Calling DAO: createTappa");
        serviceTappa.createTappa(tappa)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: createTappa started.");
                        if(voidResponse.code()==201)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: createTappa started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void createTappe(List<Tappa> tappe, Callback callback) {
        Log.i(TAG,"Calling DAO: createTappe");
        serviceTappa.createTappe(tappe)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: createTappe started.");
                        if(voidResponse.code()==201)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: createTappe started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    //Getter e Setter
    public TappaRetrofit getServiceTappa() {
        return serviceTappa;
    }

    public void setServiceTappa(TappaRetrofit serviceTappa) {
        this.serviceTappa = serviceTappa;
    }
}
