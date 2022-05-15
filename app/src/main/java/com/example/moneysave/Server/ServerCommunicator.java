package com.example.moneysave.Server;

import android.util.Log;

import com.example.moneysave.Server.boundaries.InstanceBoundary;
import com.example.moneysave.Server.boundaries.NewUserBoundary;
import com.example.moneysave.Server.boundaries.UserBoundary;
import com.example.moneysave.Server.server_interface.MyApiServer;
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerCommunicator  {
    private static ServerCommunicator serverCommunicator = new ServerCommunicator();
    private final int STATUS_OK = 200;
    private final int FOUND = 302;
    private MyApiServer myApiServer = RetrofitService.getInstance().getRetrofit().create(MyApiServer.class);

    private Callback<UserBoundary> getUserDetails_callback = new Callback<UserBoundary>() {
        @Override
        public void onResponse(Call<UserBoundary> call, Response<UserBoundary> response) {
            Log.d("myLog", response.code() + "");
            if(response.code() == STATUS_OK){
                Log.d("myLog", response.body().toString());
                DataManager.getDataManager().setUser(response.body());
            }
            else if(response.code() == FOUND){
                MyServices.getInstance().makeToast("User already exists");
                DataManager.getDataManager().failed(FOUND);
            }
            else {
                MyServices.getInstance().makeToast("User create failed");
                DataManager.getDataManager().failed(response.code());
            }
        }
        @Override
        public void onFailure(Call<UserBoundary> call, Throwable t) {
            MyServices.getInstance().makeToast(t.getMessage());
            Log.d("myLog", t.getMessage());
            DataManager.getDataManager().failed(0);
        }
    };


    private Callback<InstanceBoundary[]> searchInstancesByName_callback = new Callback<InstanceBoundary[]>() {
        @Override
        public void onResponse(Call<InstanceBoundary[]> call, Response<InstanceBoundary[]> response) {
            Log.d("myLog", response.code() + "");
            if(response.code() == STATUS_OK){
                DataManager.getDataManager().getInstancesFromServerByName(response.body());
            }
            else{
                DataManager.getDataManager().failed(response.code());
                MyServices.getInstance().makeToast("Wrong user details");
            }
        }
        @Override
        public void onFailure(Call<InstanceBoundary[]> call, Throwable t) {
            MyServices.getInstance().makeToast(t.getMessage());
            Log.d("myLog", t.getMessage());
            DataManager.getDataManager().failed(0);
        }
    };
    private Callback<InstanceBoundary> createInstance_callback = new Callback<InstanceBoundary>() {
        @Override
        public void onResponse(Call<InstanceBoundary> call, Response<InstanceBoundary> response) {
            Log.d("myLog", response.code() + "");
            if(response.code() == STATUS_OK){
                DataManager.getDataManager().getInstance(response.body());
            }
            else{
                MyServices.getInstance().makeToast("Wrong user details");
                DataManager.getDataManager().failed(response.code());
            }
        }
        @Override
        public void onFailure(Call<InstanceBoundary> call, Throwable t) {
            MyServices.getInstance().makeToast(t.getMessage());
            Log.d("myLog", t.getMessage());
            DataManager.getDataManager().failed(0);
        }
    };

    private ServerCommunicator() {

    }


    public void getUserDetails(String userDomain, String userEmail) { //"2022b.Lilach.Laniado", "rogygggyn@gmail.com"
        myApiServer.getUserDetails(userDomain , userEmail )
                .enqueue(getUserDetails_callback);
    }

    public void createUser(NewUserBoundary newUserBoundary) {
        myApiServer.createUser(newUserBoundary)
                .enqueue(getUserDetails_callback);
    }


    public void updateUserDetails(String userDomain, String userEmail, UserBoundary userBoundary) {
        // TODO: 14/05/2022
    }


   // public void getInstanceDetails(String instanceDomain, String instanceId , String userDomain, String userEmail){
      //  myApiServer.getInstanceDetails(instanceDomain,instanceId, userDomain, userEmail)
            //    .enqueue(getUserDetails_callback);
   // }
    // TODO: 15/05/2022


    public void createInstance(InstanceBoundary instanceBoundary) {
        myApiServer.createInstance(instanceBoundary)
                .enqueue(createInstance_callback);
    }

    public void  searchInstancesByName(String name, String userDomain, String userEmail){
        myApiServer.searchInstancesByName(name , userDomain, userEmail)
                .enqueue(searchInstancesByName_callback);
    }


    public static ServerCommunicator getInstance(){
        return serverCommunicator;
    }



}
