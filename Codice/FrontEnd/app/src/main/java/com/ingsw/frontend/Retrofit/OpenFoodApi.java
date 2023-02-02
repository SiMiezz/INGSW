package com.ingsw.frontend.Retrofit;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface OpenFoodApi {

    @Headers({"User-Agent: ratatouille23 - Android - Version 1.0"})
    @GET("/cgi/search.pl")
    Single<String> getProductList(@Query("search_terms") String search_terms,
                                  @Query("search_simple") Boolean search_simple,
                                  @Query("json") Boolean json,
                                  @Query("fields") String fields);
}
