package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Element;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ElementApi {

    @POST("/element/create")
    Single<Void> create(@Body Element element);

    @DELETE("/element/delete/{id}")
    Single<Void> deleteById(@Path("id") Integer id);

    @GET("/element/get/{id}")
    Single<List<Element>> getByCategoryId(@Path("id") Integer id);
}
