package com.example.moneysave.tools;

import android.content.Context;
import android.widget.Toast;

public class MyServices {

    private static MyServices myServices = new MyServices();
    private Context context;
    private MyServices() {

    }

    public MyServices setContext(Context context) {
        this.context = context.getApplicationContext();
        return this;
    }

    public static MyServices getInstance(){
        return myServices;
    }

    public void makeToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
