package com.example.natour21.Entity.DAO.CRetrofitDAO;

import android.util.Log;

import com.example.natour21.Entity.DAO.IRetrofitDAO.IFotoItinerarioDAO;
import com.example.natour21.Entity.FotoItinerario;
import com.example.natour21.Utils.Enumeration.API;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Request.Instance.FotoItinerarioRetrofit;
import com.example.natour21.Utils.Request.RequestGenerator;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class FotoItinerarioDAO implements IFotoItinerarioDAO {

    private FotoItinerarioRetrofit serviceFotoItinerario;
    private final String TAG = "FotoItinerarioDAO";

    //Constructor
    public FotoItinerarioDAO(){
        this.serviceFotoItinerario = RequestGenerator.retrofitInstance(API.FOTO_ITINERARIO)
                                                        .create(FotoItinerarioRetrofit.class);
    }

    //Methods
    @Override
    public void listFotoItinerario(Callback callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getFotoItinerarioByID(Long idFoto, Callback callback) {
        Log.i(TAG,"Calling DAO: getFotoItinerarioByID");
        serviceFotoItinerario.getFotoItinerarioByID(idFoto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<FotoItinerario>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull FotoItinerario fotoItinerario) {
                        Log.i(TAG, "onSuccess DAO: getFotoItinerarioByID started.");
                        callback.onSuccess(fotoItinerario);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getFotoItinerarioByID started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getFotoItinerarioByItinerario(Long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: getFotoItinerarioByItinerario");
        serviceFotoItinerario.getFotoItinerarioByItinerario(idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<FotoItinerario>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<FotoItinerario> fotoItinerario) {
                        Log.i(TAG, "onSuccess DAO: getFotoItinerarioByItinerario started.");
                        callback.onSuccess(fotoItinerario);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getFotoItinerarioByItinerario started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getCountFotoItinerario(Long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: getCountFotoItinerario");
        serviceFotoItinerario.getCountFotoItinerario(idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Long fotoItinerario) {
                        Log.i(TAG, "onSuccess DAO: getCountFotoItinerario started.");
                        callback.onSuccess(fotoItinerario);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getCountFotoItinerario started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void publshFoto(FotoItinerario foto, Callback callback) {
        Log.i(TAG,"Calling DAO: publshFoto");
        serviceFotoItinerario.publshFoto(foto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: publshFoto started.");
                        if(voidResponse.code()==201)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: publshFoto started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void deleteFoto(Long idFoto, Callback callback) {
        Log.i(TAG,"Calling DAO: deleteFoto");
        serviceFotoItinerario.deleteFoto(idFoto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: deleteFoto started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: deleteFoto started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    //Getter e Setter
    public FotoItinerarioRetrofit getServiceFotoItinerario() {
        return serviceFotoItinerario;
    }

    public void setServiceFotoItinerario(FotoItinerarioRetrofit serviceFotoItinerario) {
        this.serviceFotoItinerario = serviceFotoItinerario;
    }

}
