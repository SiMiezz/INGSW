package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Element;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ElementApi {

    @POST("/element/create")
    Completable create(@Body Element element);

    @HTTP(method = "DELETE", path="/element/delete",hasBody = true)
    Completable delete(@Body Element element);

    @GET("/element/get/{id}")
    Single<List<Element>> getByCategoryId(@Path("id") Integer id);

    @GET("/element/get/{id}/order")
    Single<List<Element>> getElementByMenuId(@Path("id") Integer id);
}
