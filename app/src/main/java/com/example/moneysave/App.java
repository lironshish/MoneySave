package com.example.moneysave;

import android.app.Application;

import com.example.moneysave.tools.MyServices;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MyServices.getInstance().setContext(this);

    }


}
