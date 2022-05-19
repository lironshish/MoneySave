package com.example.moneysave.Objects;

import android.provider.ContactsContract;

import com.example.moneysave.Server.boundaries.UserBoundary;
import com.example.moneysave.Server.boundaries.UserId;
import com.example.moneysave.Server.boundaries.UserRole;

import java.util.ArrayList;
import java.util.List;

public class MyUser extends UserBoundary {


    private String password;
    private List<Account> myAccounts;
    private UserDetails userDetails;

    public MyUser(){
        super();
    }

    public MyUser(UserRole role, String userName, String avatar, UserId userId){
        super(role, userName, avatar, userId);
        setPassword(password);
        myAccounts = new ArrayList<>();
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

    public List<Account> getMyAccounts() {
        return myAccounts;
    }

    public MyUser setMyAccounts(List<Account> myAccounts) {
        this.myAccounts = myAccounts;
        return this;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public MyUser setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        return this;
    }
//Need to add functions that pull and push data to the database
}
