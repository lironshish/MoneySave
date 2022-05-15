package com.example.moneysave.call_backs;

import com.example.moneysave.Objects.MyUser;
import com.example.moneysave.Objects.UserPassword;

public interface LoginCallBack extends ServerCallback {
    void login(UserPassword userPassword);
    void getUser(MyUser myUser);

}

