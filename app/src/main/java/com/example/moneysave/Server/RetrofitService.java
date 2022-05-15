package com.example.moneysave.Server;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static RetrofitService retrofitService = new RetrofitService() ;
    private Retrofit retrofit;

    private RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.96:8085")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public  Retrofit getRetrofit() {
        return retrofit;
    }

    public  static RetrofitService getInstance(){
        return retrofitService;
    }
}