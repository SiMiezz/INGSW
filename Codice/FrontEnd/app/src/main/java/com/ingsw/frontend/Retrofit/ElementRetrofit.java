package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Element;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ElementRetrofit {

    @POST("/element/create")
    Call<Element> create(@Body Element element);

    @DELETE("/element/delete/{id}")
    Call<Void> deleteById(@Path("id") Integer id);

    @GET("/element/get/{id}")
    Call<List<Element>> getByCategoryId(@Path("id") Integer id);
}
