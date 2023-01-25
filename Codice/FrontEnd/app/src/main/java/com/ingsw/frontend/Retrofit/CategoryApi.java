package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryApi {

    @POST("/category/create")
    Completable create(@Body Category category);

    @GET("/category/get/{id}")
    Single<List<Category>> getByMenuId(@Path("id") Integer id);

    @GET("/category/get/{id}/{aliment_type}")
    Single<List<Category>> getByMenuIdAndAliment(@Path("id") Integer id,@Path("aliment_type") Aliment_Type aliment_type);

    @HTTP(method = "DELETE", path= "/category/delete", hasBody = true)
    Completable delete(@Body Category category);
}
