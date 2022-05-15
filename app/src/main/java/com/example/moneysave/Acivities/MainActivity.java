package com.example.moneysave.Acivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysave.Objects.MyUser;
import com.example.moneysave.Objects.UserPassword;
import com.example.moneysave.R;
import com.example.moneysave.Server.ServerCommunicator;
import com.example.moneysave.call_backs.LoginCallBack;
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText main_EDT_email;
    private TextInputEditText main_EDT_password;
    private MaterialButton main_BTN_sign_in;
    private ExtendedFloatingActionButton main_FAB_sign_up;
    private MyUser main_user;
    private Intent accounts_activity;
    private Bundle bundle;
    private boolean callInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        bundle = new Bundle();
        accounts_activity = new Intent(this, AccountActivity.class);
        findViews();
        init();
    }

    private void findViews() {
        main_EDT_email = findViewById(R.id.main_EDT_email);
        main_EDT_password = findViewById(R.id.main_EDT_password);
        main_BTN_sign_in = findViewById(R.id.main_BTN_sign_in);
        main_FAB_sign_up = findViewById(R.id.main_FAB_sign_up);

    }

    private void init() {// , "rogygggyn@gmail.com"
        DataManager.getDataManager().setActiveCallBack(loginCallBack); // TODO: 14/05/2022 verify user input data ok
        main_BTN_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!callInProgress) {
                    callInProgress = true;
                    ServerCommunicator.getInstance().getUserDetails(DataManager.MAIN_DOMAIN, main_EDT_email.getText().toString());
                }
            }
        });

        main_FAB_sign_up.setOnClickListener(view -> {
            Intent myIntent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(myIntent);
            finish();
        });
    }

    private LoginCallBack loginCallBack = new LoginCallBack() {
        @Override
        public void login(UserPassword userPassword) {
            if (userPassword != null) {
                if (main_user.getPassword().equals(userPassword.getInstanceAttributes().get(DataManager.KEY_PASSWORD))) {
                    DataManager.getDataManager().setActiveCallBack(null);
                    Intent myIntent = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(myIntent);
                    finish();
                } else {
                    MyServices.getInstance().makeToast("Wrong password");
                    callInProgress = false;
                }
            }
            else{
                callInProgress = false;
            }
        }

        @Override
        public void getUser(MyUser myUser) {
            UserPassword userPassword = new UserPassword(main_EDT_password.getText().toString());
            main_user = myUser;
            main_user.setPassword(main_EDT_password.getText().toString());
            ServerCommunicator.getInstance().searchInstancesByName(userPassword.getName(), myUser.getUserId().getDomain(), myUser.getUserId().getEmail());
        }

        @Override
        public void failed(int status_code) {
            callInProgress = false;
        }

        @Override
        public void empty() {
            callInProgress = false;
        }
    };

}

