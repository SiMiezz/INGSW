package com.example.natour21.Entity.DAO.CRetrofitDAO;

import android.util.Log;

import com.example.natour21.Entity.DAO.IRetrofitDAO.IItinerarioDAO;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Utils.Enumeration.API;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Request.Instance.ItinerarioRetrofit;
import com.example.natour21.Utils.Request.RequestGenerator;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class ItinerarioDAO implements IItinerarioDAO {

    private ItinerarioRetrofit serviceItinerario;
    private final String TAG = "ItinerarioDAO";

    //Constructor
    public ItinerarioDAO(){
        this.serviceItinerario = RequestGenerator.retrofitInstance(API.ITINERARIO_API)
                                                    .create(ItinerarioRetrofit.class);
    }

    //Methods
    @Override
    public void listItinerari(Callback callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getAllRecent(Callback callback) {
        Log.i(TAG,"Calling DAO: getAllRecent");
        serviceItinerario.getAllRecent()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Itinerario>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<Itinerario> itinerari) {
                        Log.i(TAG, "onSuccess DAO: getAllRecent started.");
                        callback.onSuccess(itinerari);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getAllRecent started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getItinerariByName(String nomeItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: getItinerariByName");
        serviceItinerario.getItinerariByName(nomeItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Itinerario>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<Itinerario> itinerari) {
                        Log.i(TAG, "onSuccess DAO: getItinerariByName started.");
                        callback.onSuccess(itinerari);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getItinerariByName started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getItinerarioByID(Long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: getItinerarioByID");
        serviceItinerario.getItinerarioByID(idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Itinerario>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Itinerario itinerario) {
                        Log.i(TAG, "onSuccess DAO: getItinerarioByID started.");
                        callback.onSuccess(itinerario);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getItinerarioByID started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getItinerarioByUsername(String username, Callback callback) {
        Log.i(TAG,"Calling DAO: getItinerarioByUsername");
        serviceItinerario.getItinerarioByUsername(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Itinerario>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<Itinerario> itinerari) {
                        Log.i(TAG, "onSuccess DAO: getItinerarioByUsername started.");
                        callback.onSuccess(itinerari);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getItinerarioByUsername started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getItinerarioByFilter(Map<String,String> filtro, Callback callback) {
        Log.i(TAG,"Calling DAO: getItinerarioByFilter");
        serviceItinerario.getItinerarioByFilter(filtro)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Itinerario>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<Itinerario> itinerari) {
                        Log.i(TAG, "onSuccess DAO: getItinerarioByFilter started.");
                        callback.onSuccess(itinerari);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getItinerarioByFilter started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void createItinerario(Itinerario itinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: createItinerario");
        serviceItinerario.createItinerario(itinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: createItinerario started.");
                        if(voidResponse.code()==201){
                            String id_itinerario = voidResponse.headers().get("id_itinerario");
                            callback.onSuccess(Long.parseLong(id_itinerario));
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: createItinerario started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void modifyItinerario(Itinerario itinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: modifyItinerario");
        serviceItinerario.modifyItinerario(itinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: modifyItinerario started.");
                        String id_itinerario = voidResponse.headers().get("id_itinerario");
                        if(voidResponse.code()==200)
                            callback.onSuccess(Long.parseLong(id_itinerario));
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: modifyItinerario started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void removeItinerario(long id_itinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: removeItinerario");
        serviceItinerario.removeItinerario(id_itinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: removeItinerario started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: removeItinerario started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    //Getter e Setter
    public ItinerarioRetrofit getServiceItinerario() {
        return serviceItinerario;
    }

    public void setServiceItinerario(ItinerarioRetrofit serviceItinerario) {
        this.serviceItinerario = serviceItinerario;
    }
}
