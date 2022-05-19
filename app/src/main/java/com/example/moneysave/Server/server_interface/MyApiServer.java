package com.example.moneysave.Server.server_interface;

import com.example.moneysave.Server.boundaries.InstanceBoundary;
import com.example.moneysave.Server.boundaries.NewUserBoundary;
import com.example.moneysave.Server.boundaries.UserBoundary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiServer {


    @GET("/iob/users/login/{userDomain}/{userEmail}")
    Call<UserBoundary> getUserDetails(@Path("userDomain") String userDomain , @Path("userEmail") String userEmail);

    @POST("/iob/users")
    Call<UserBoundary> createUser(@Body NewUserBoundary newUserBoundary);

//    @PUT("/iob/users/{userDomain}/{userEmail}")
//    Call<UserBoundary> updateUserDetails(@Path("userDomain") String userDomain , @Path("userEmail") String userEmail, @Body UserBoundary userBoundary);

    @GET("/iob/instances/{instanceDomain}/{instanceId}")
    Call<InstanceBoundary> getInstanceDetails(
            @Path("instanceDomain") String instanceDomain,
            @Path("instanceId") String instanceId ,
            @Query("userDomain") String userDomain,
            @Query("userEmail") String userEmail);

    @POST("/iob/instances")
    Call<InstanceBoundary> createInstance(@Body InstanceBoundary instanceBoundary);

    @GET("/iob/instances/search/byName/{name}")
    Call<InstanceBoundary[]> searchInstancesByName(
            @Path("name") String name,
            @Query("userDomain") String userDomain,
            @Query("userEmail") String userEmail);

    @PUT("/iob/instances/{instanceDomain}/{instanceId}")
    Call<Void> updateInstanceDetails(@Body InstanceBoundary instanceBoundary);


}