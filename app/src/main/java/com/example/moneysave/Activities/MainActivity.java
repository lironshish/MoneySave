package com.example.moneysave.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysave.Objects.Account;
import com.example.moneysave.Objects.MyUser;
import com.example.moneysave.Objects.UserDetails;
import com.example.moneysave.R;
import com.example.moneysave.Server.ServerCommunicator;
import com.example.moneysave.Server.boundaries.InstanceId;
import com.example.moneysave.call_backs.GetAccounts_callback;
import com.example.moneysave.call_backs.LoginCallBack;
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        DataManager.getDataManager().setActiveCallBack(loginCallBack);

        main_BTN_sign_in.setOnClickListener(view -> signInClicked());

        main_FAB_sign_up.setOnClickListener(view -> {
            Intent myIntent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(myIntent); // TODO: 18/05/2022 finish???
            //finish();
        });
    }

    private void signInClicked() {
        if (!isValidEmail(main_EDT_email.getText().toString())) {
            MyServices.getInstance().makeToast("Invalid Email! Try again.");
        } else if (main_EDT_password.getText().toString().isEmpty()) {
            MyServices.getInstance().makeToast("Password cannot be empty! Try again.");
        } else if (!callInProgress) {
            callInProgress = true;
            ServerCommunicator.getInstance().getUserDetails(DataManager.MAIN_DOMAIN, main_EDT_email.getText().toString());
        }
    }

    private boolean isValidEmail(String email) {
        //Regular Expression
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private LoginCallBack loginCallBack = new LoginCallBack() {
        @Override
        public void login(UserDetails userDetails) {
            if (userDetails != null) {
                if (main_user.getPassword().equals(userDetails.getInstanceAttributes().get(DataManager.KEY_PASSWORD))) {
                    DataManager.getDataManager().setActiveCallBack(null);
                    main_user.setUserDetails(userDetails);
                    if (((ArrayList<InstanceId>) userDetails.getInstanceAttributes().get(DataManager.KEY_MY_ACCOUNTS)).size() != 0) {
                        getAllAccount(userDetails);

                    } else {
                        Intent myIntent = new Intent(MainActivity.this, MyAccountsActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                } else {
                    MyServices.getInstance().makeToast("Wrong password");
                    callInProgress = false;
                }
            } else {
                callInProgress = false;
            }
        }

        @Override
        public void getUser(MyUser myUser) {
            UserDetails userPassword = new UserDetails(main_EDT_password.getText().toString());
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

    private void getAllAccount(UserDetails userDetails) {
        DataManager.getDataManager().setActiveCallBack(getAccounts_callback);
        ((ArrayList<InstanceId>) userDetails.getInstanceAttributes().get(DataManager.KEY_MY_ACCOUNTS))
                .forEach(accountId -> ServerCommunicator
                                    .getInstance()
                                    .getInstanceDetails(accountId.getDomain() , accountId.getId() , main_user.getUserId().getDomain() , main_user.getUserId().getEmail() ));
    }
    private GetAccounts_callback getAccounts_callback = new GetAccounts_callback() {
        @Override
        public void getAccount() {
            if(main_user.getMyAccounts().size() == main_user.getUserDetails().receive_myAccounts().size()) {
                DataManager.getDataManager().setActiveCallBack(null);
                Intent myIntent = new Intent(MainActivity.this, MyAccountsActivity.class);
                startActivity(myIntent);
                finish();
            }
        }
        @Override
        public void failed(int status_code) {

        }
        @Override
        public void empty() {

        }
    } ;


}

