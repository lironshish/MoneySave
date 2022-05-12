package com.example.moneysave.Objects;

import java.util.ArrayList;

public class Account {
    private String name;
    private ArrayList<User> SharedWith = new ArrayList<User>();

    public Account(){ }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getSharedWith() {
        return SharedWith;
    }

    public void setSharedWith(ArrayList<User> sharedWith) {
        SharedWith = sharedWith;
    }

    //Need to add functions that pull and push data to the database

}
