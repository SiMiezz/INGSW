package com.ingsw.frontend.Retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    public void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.221/8080")//inserire ip server macchina
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
