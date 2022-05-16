package com.example.moneysave.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysave.Objects.MyUser;
import com.example.moneysave.Objects.UserPassword;
import com.example.moneysave.R;
import com.example.moneysave.Server.ServerCommunicator;
import com.example.moneysave.Server.boundaries.NewUserBoundary;
import com.example.moneysave.Server.boundaries.UserRole;
import com.example.moneysave.call_backs.LoginCallBack;
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    //private MaterialToolbar sign_up_toolbar;
    private TextInputEditText sign_up_EDT_user_name;
    private TextInputEditText sign_up_EDT_email;
    private TextInputEditText sign_up_EDT_password;
    private MaterialButton sign_up_BTN_sign_up;
    private boolean callInProgress = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        findViews();
        init();
    }


    private void findViews() {
        //toolbar
        //sign_up_toolbar = findViewById(R.id.signUp_toolbar);
        //setSupportActionBar(sign_up_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        sign_up_EDT_user_name = findViewById(R.id.sign_up_EDT_user_name);
        sign_up_EDT_email = findViewById(R.id.sign_up_EDT_email);
        sign_up_EDT_password = findViewById(R.id.sign_up_EDT_password);
        sign_up_BTN_sign_up = findViewById(R.id.sign_up_BTN_sign_up);
    }

    private void init() {
        sign_up_BTN_sign_up.setOnClickListener(view -> {
            if(callInProgress)
                return;

            if (!isEmailOk()) {
                MyServices.getInstance().makeToast("Wrong Email");
                return;
            }
            if (!isPasswordOk()) {
                MyServices.getInstance().makeToast("password cannot be empty");
                return;
            }
            if (!isUserNameOk()) {
                MyServices.getInstance().makeToast("username cannot be empty");
                return;
            }
            callInProgress = true;
            DataManager.getDataManager().setActiveCallBack(loginCallBack);
            NewUserBoundary newUserBoundary = new NewUserBoundary();
            newUserBoundary.setAvatar("aaa"); //  TODO: 14/05/2022 add avatar!
            newUserBoundary.setRole(UserRole.MANAGER);
            newUserBoundary.setUsername(sign_up_EDT_user_name.getText().toString());
            newUserBoundary.setEmail(sign_up_EDT_email.getText().toString());
            ServerCommunicator.getInstance().createUser(newUserBoundary);
        });
    }
    // TODO: 15/05/2022 change all patterns they not good  and update messages explantaion
    private boolean isEmailOk() {
        //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
       // return sign_up_EDT_email.getText().toString().matches(emailPattern);
        String email = sign_up_EDT_email.getText().toString();
        return (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isPasswordOk() {
        return !sign_up_EDT_password.getText().toString().isEmpty();
    }

    private boolean isUserNameOk() {
        return !sign_up_EDT_user_name.getText().toString().isEmpty();
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    private LoginCallBack loginCallBack = new LoginCallBack() {
        @Override
        public void login(UserPassword userPassword) {
            DataManager.getDataManager().setActiveCallBack(null);
            DataManager.getDataManager().getMyUser().setPassword((String) userPassword.getInstanceAttributes().get(DataManager.KEY_PASSWORD));
            Intent myIntent = new Intent(SignUpActivity.this, MyAccountsActivity.class);
            startActivity(myIntent);
            finish();

        }

        @Override
        public void getUser(MyUser myUser) {
            UserPassword userPassword = new UserPassword(sign_up_EDT_password.getText().toString());
            ServerCommunicator.getInstance().createInstance(userPassword);
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

