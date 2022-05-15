package com.example.moneysave.tools;


import com.example.moneysave.Objects.MyUser;
import com.example.moneysave.Objects.UserPassword;
import com.example.moneysave.Server.boundaries.InstanceBoundary;
import com.example.moneysave.Server.boundaries.UserBoundary;
import com.example.moneysave.Server.boundaries.UserRole;
import com.example.moneysave.call_backs.LoginCallBack;
import com.example.moneysave.call_backs.ServerCallback;
import com.example.moneysave.call_backs.getInstancesCallBack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataManager {
    private static DataManager _instance = new DataManager();
    public static final String MAIN_DOMAIN = "2022b.Lilach.Laniado";
    public static final String KEY_PASSWORD = "KEY_PASSWORD";
    private MyUser myUser = null;
    private ServerCallback activeCallBack;

    private DataManager() {
    }


    public void setUser(UserBoundary userBoundary) {
        if (userBoundary == null) {
            activeCallBack.failed(0);
            return;
        }

        myUser = new MyUser(userBoundary);
        if (activeCallBack instanceof LoginCallBack)
            ((LoginCallBack) activeCallBack).getUser(myUser);
        else
            activeCallBack.failed(0);

    }

    public MyUser getMyUser() {
        return myUser;
    }


    public DataManager setActiveCallBack(ServerCallback activeCallBack) {
        this.activeCallBack = activeCallBack;
        return this;
    }

    public static DataManager getDataManager() {
        return _instance;
    }


    public void getInstancesFromServerByName(InstanceBoundary[] body) {
        if (body == null || body.length <= 0) {
            this.activeCallBack.empty();
            return;
        }

        List<InstanceBoundary> instanceBoundaries = new ArrayList<>(Arrays.asList(body));
        if (activeCallBack instanceof LoginCallBack)
            ((LoginCallBack) activeCallBack).login(new UserPassword(instanceBoundaries.get(0)));


        //this.instancesCallBack.Instances(instanceBoundaries);
    }

    public void failed(int code) {
        this.activeCallBack.failed(code);
    }

    public void getInstance(InstanceBoundary body) {
        if (body == null)
            activeCallBack.empty();
        if (activeCallBack instanceof LoginCallBack)
            ((LoginCallBack) activeCallBack).login(new UserPassword(body));
    }
}
