package com.example.moneysave;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Server {


    @GET("/iob/users/login/2022b.Lilach.Laniado/rogygggyn@gmail.com")
    Call<String> get();

    @POST("/employee/save")
    Call<String> save(@Body String employee);
}