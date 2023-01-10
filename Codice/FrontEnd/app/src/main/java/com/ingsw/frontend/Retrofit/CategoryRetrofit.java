package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryRetrofit {

    @POST("/category/create")
    Call<Category> create(@Body Category category);

    @DELETE("/category/delete/{id}")
    Call<Void> deleteById(@Path("id") Integer id);

    @GET("/category/get/{code}")
    Call<List<Category>> getByMenuQrCode(@Path("code") String code);

}
