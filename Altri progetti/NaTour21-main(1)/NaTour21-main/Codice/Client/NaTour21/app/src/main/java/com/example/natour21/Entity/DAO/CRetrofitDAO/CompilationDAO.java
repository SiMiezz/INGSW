package com.example.natour21.Entity.DAO.CRetrofitDAO;

import android.util.Log;

import com.example.natour21.Entity.Compilation;
import com.example.natour21.Entity.DAO.IRetrofitDAO.ICompilationDAO;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Utils.Enumeration.API;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Request.Instance.CompilationRetrofit;
import com.example.natour21.Utils.Request.RequestGenerator;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class CompilationDAO implements ICompilationDAO {

    private CompilationRetrofit serviceCompilation;
    private final String TAG = "CompilationDAO";

    //Constructor
    public CompilationDAO(){
        this.serviceCompilation = RequestGenerator.retrofitInstance(API.COMPILATION_API)
                                                    .create(CompilationRetrofit.class);
    }

    //Methods
    @Override
    public void listCompilation(Callback callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getCompilationById(long idCompilation, Callback callback) {
        Log.i(TAG,"Calling DAO: getCompilationByID");
        serviceCompilation.getCompilationByID(idCompilation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Compilation>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Compilation compilation) {
                        Log.i(TAG, "onSuccess DAO: getCompilationByID started.");
                        callback.onSuccess(compilation);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getCompilationByID started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getCompilationByUsername(String username, Callback callback) {
        Log.i(TAG,"Calling DAO: getCompilationByUsername");
        serviceCompilation.getCompilationByUsername(username)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Compilation>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<Compilation> compilation) {
                        Log.i(TAG, "onSuccess DAO: getCompilationByUsername started.");
                        callback.onSuccess(compilation);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getCompilationByUsername started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getItinerariInCompilation(long idCompilation, Callback callback) {
        Log.i(TAG,"Calling DAO: getItinerariInCompilation");
        serviceCompilation.getItinerariInCompilation(idCompilation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Itinerario>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<Itinerario> itinerari) {
                        Log.i(TAG, "onSuccess DAO: getItinerariInCompilation started.");
                        callback.onSuccess(itinerari);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getItinerariInCompilation started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getCompilationValidSave(String username, long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: getCompilationValidSave");
        serviceCompilation.getCompilationValidSave(username, idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<String> valid) {
                        Log.i(TAG, "onSuccess DAO: getCompilationValidSave started.");
                        callback.onSuccess(valid);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getCompilationValidSave started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });

    }

    @Override
    public void postCompilation(Compilation compilation, Callback callback) {
        Log.i(TAG,"Calling DAO: postCompilation");
        serviceCompilation.postCompilation(compilation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: postCompilation started.");
                        if(voidResponse.code()==201)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: postCompilation started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void postItinerarioInCompilation(long idCompilation, long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: postCompilation");
        serviceCompilation.postItinerarioInCompilation(idCompilation,idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: postItinerarioInCompilation started.");
                        if(voidResponse.code()==201)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: postItinerarioInCompilation started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void putCompilation(Compilation compilation, Callback callback) {
        Log.i(TAG,"Calling DAO: postCompilation");
        serviceCompilation.putCompilation(compilation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: postItinerarioInCompilation started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: postItinerarioInCompilation started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void deleteCompilation(long idCompilation, Callback callback) {
        Log.i(TAG,"Calling DAO: deleteCompilation");
        serviceCompilation.deleteCompilation(idCompilation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: deleteCompilation started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: deleteCompilation started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void deleteItinerarioInCompilation(long idCompilation, long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: deleteItinerarioInCompilation");
        serviceCompilation.deleteItinerarioInCompilation(idCompilation,idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: deleteItinerarioInCompilation started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: deleteItinerarioInCompilation started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    //Getter e Setter
    public CompilationRetrofit getServiceCompilation() {
        return serviceCompilation;
    }

    public void setServiceCompilation(CompilationRetrofit serviceCompilation) {
        this.serviceCompilation = serviceCompilation;
    }

}
