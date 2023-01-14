package com.ingsw.frontend.Service.Class;

import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Interface.IElementService;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Retrofit.ElementApi;
import com.ingsw.frontend.Retrofit.RetrofitService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ElementService implements IElementService {

    private ElementApi elementApi;

    public ElementService() {
        this.elementApi = RetrofitService.getRetrofit().create(ElementApi.class);
    }

    @Override
    public void create(Callback callback, Element element){}

    @Override
    public void deleteById(Callback callback, Integer id){}

    @Override
    public void getByCategoryId(Callback callback,Integer id){
        elementApi.getByCategoryId(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<Element>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onSuccess(@NonNull List<Element> elementList) {
                        callback.returnResult(elementList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e);
                    }
                });
    }
}
