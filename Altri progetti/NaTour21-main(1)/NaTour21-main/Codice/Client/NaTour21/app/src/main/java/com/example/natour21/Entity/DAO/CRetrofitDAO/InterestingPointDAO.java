package com.example.natour21.Entity.DAO.CRetrofitDAO;

import android.util.Log;

import com.example.natour21.Entity.DAO.IRetrofitDAO.IInterestingPointDAO;
import com.example.natour21.Entity.InterestingPoint;
import com.example.natour21.Utils.Enumeration.API;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Request.Instance.InterestingPointRetrofit;
import com.example.natour21.Utils.Request.RequestGenerator;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class InterestingPointDAO implements IInterestingPointDAO {

    private InterestingPointRetrofit serviceInterestingPoint;
    private final String TAG = "InterestingPointDAO";

    //Constructor
    public  InterestingPointDAO(){
        this.serviceInterestingPoint = RequestGenerator.retrofitInstance(API.INTERESTINGPOINT_API)
                                                        .create(InterestingPointRetrofit.class);
    }

    //Methods
    @Override
    public void listInterestingPoint(Callback callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getCompilationByID(long idInterestingPoint, Callback callback) {
        Log.i(TAG,"Calling DAO: getCompilationByID");
        serviceInterestingPoint.getCompilationByID(idInterestingPoint)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<InterestingPoint>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull InterestingPoint interestingPoint) {
                        Log.i(TAG, "onSuccess DAO: getCompilationByID started.");
                        callback.onSuccess(interestingPoint);
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
    public void getInterestingPointByItinerario(long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: getInterestingPointByItinerario");
        serviceInterestingPoint.getInterestingPointByItinerario(idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<InterestingPoint>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<InterestingPoint> interestingPoints) {
                        Log.i(TAG, "onSuccess DAO: getInterestingPointByItinerario started.");
                        callback.onSuccess(interestingPoints);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getInterestingPointByItinerario started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getFotoItinerarioSingle(long idInterestingPoint, Callback callback) {
        Log.i(TAG,"Calling DAO: getFotoItinerarioSingle");
        serviceInterestingPoint.getFotoItinerarioSingle(idInterestingPoint)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull String photo) {
                        Log.i(TAG, "onSuccess DAO: getFotoItinerarioSingle started.");
                        callback.onSuccess(photo);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getFotoItinerarioSingle started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getFotoItinerarioMultiple(long idItinerario, Callback callback) {
        Log.i(TAG,"Calling DAO: getFotoItinerarioMultiple");
        serviceInterestingPoint.getFotoItinerarioMultiple(idItinerario)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull List<String> photos) {
                        Log.i(TAG, "onSuccess DAO: getFotoItinerarioMultiple started.");
                        callback.onSuccess(photos);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: getFotoItinerarioMultiple started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void createInterestingPoint(InterestingPoint interestingPoint, Callback callback) {
        Log.i(TAG,"Calling DAO: createInterestingPoint");
        serviceInterestingPoint.createInterestingPoint(interestingPoint)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: createInterestingPoint started.");
                        if(voidResponse.code()==201)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: createInterestingPoint started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void createInterestingPoints(List<InterestingPoint> interestingPoints, Callback callback) {
        Log.i(TAG,"Calling DAO: createInterestingPoints");
        serviceInterestingPoint.createInterestingPoints(interestingPoints)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: createInterestingPoints started.");
                        if(voidResponse.code()==201)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: createInterestingPoints started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void modifyInterestingPoint(InterestingPoint interestingPoint, Callback callback) {
        Log.i(TAG,"Calling DAO: modifyInterestingPoint");
        serviceInterestingPoint.modifyInterestingPoint(interestingPoint)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: modifyInterestingPoint started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: modifyInterestingPoint started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void deleteInterestingPoint(long idInterestingPoint, Callback callback) {
        Log.i(TAG,"Calling DAO: deleteInterestingPoint");
        serviceInterestingPoint.deleteInterestingPoint(idInterestingPoint)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: deleteInterestingPoint started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: deleteInterestingPoint started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void deleteFotoInterestingPoint(long idInterestingPoint, Callback callback) {
        Log.i(TAG,"Calling DAO: deleteFotoInterestingPoint");
        serviceInterestingPoint.deleteFotoInterestingPoint(idInterestingPoint)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Void>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        Log.i(TAG, "onSuccess DAO: deleteFotoInterestingPoint started.");
                        if(voidResponse.code()==200)
                            callback.onSuccess(true);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError DAO: deleteInterestingPoint started.");
                        Log.e(TAG, e.toString());
                        callback.onFailure(e);
                    }
                });
    }

    //Getter e Setter
    public InterestingPointRetrofit getServiceInterestingPoint() {
        return serviceInterestingPoint;
    }

    public void setServiceInterestingPoint(InterestingPointRetrofit serviceInterestingPoint) {
        this.serviceInterestingPoint = serviceInterestingPoint;
    }

}
