package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Menu;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuApi {

    @GET("/menu/get/{name}")
    Observable<Menu> getByRestaurantName(@Path("name") String name);
}
