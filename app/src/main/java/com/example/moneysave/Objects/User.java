package com.example.moneysave.Objects;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class User {
    private String name;
    private ContactsContract.CommonDataKinds.Email email;
    private String password;
    //TODO: Add Avatar variable
    private ArrayList<Account> myAccounts = new ArrayList<Account>();



    public User(){ }

    public User(String name, ContactsContract.CommonDataKinds.Email email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactsContract.CommonDataKinds.Email getEmail() {
        return email;
    }

    public void setEmail(ContactsContract.CommonDataKinds.Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Need to add functions that pull and push data to the database
}
