package com.example.moneysave.Objects;

import android.provider.ContactsContract;

import com.example.moneysave.Server.boundaries.UserBoundary;
import com.example.moneysave.Server.boundaries.UserId;
import com.example.moneysave.Server.boundaries.UserRole;

public class MyUser extends UserBoundary {


    private String password;

    public MyUser(){
        super();
    }

    public MyUser(UserRole role, String userName, String avatar, UserId userId){
        super(role, userName, avatar, userId);
        setPassword(password);
    }

    public MyUser(UserBoundary userBoundary) {
        super(userBoundary.getRole(), userBoundary.getUsername(), userBoundary.getAvatar(), userBoundary.getUserId());
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //Need to add functions that pull and push data to the database
}
