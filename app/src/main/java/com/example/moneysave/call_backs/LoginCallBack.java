package com.example.moneysave.call_backs;

import com.example.moneysave.Objects.MyUser;
import com.example.moneysave.Objects.UserDetails;

public interface LoginCallBack extends ServerCallback {
    void login(UserDetails userPassword);
    void getUser(MyUser myUser);

}

