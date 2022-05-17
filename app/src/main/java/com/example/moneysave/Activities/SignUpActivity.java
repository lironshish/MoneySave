package com.example.moneysave.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    //private MaterialToolbar sign_up_toolbar;
    private TextInputEditText sign_up_EDT_user_name;
    private TextInputEditText sign_up_EDT_email;
    private TextInputEditText sign_up_EDT_password;
    private MaterialButton sign_up_BTN_sign_up;
    private FloatingActionButton fab_return;
    private LinearLayout linear_avaters;
    private ImageView chosenAvatar;
    private HashMap<Integer ,String> avatarsIdNameMap;
    private boolean callInProgress = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        avatarsIdNameMap = new HashMap<>();
        findViews();
        init();
        initAvatarImages();
    }

    private void initAvatarImages(){
        for(int i =0 ; i< linear_avaters.getChildCount(); i++){
            ImageView current = (ImageView) linear_avaters.getChildAt(i);
            avatarsIdNameMap.put(current.getId() , "avatar" + (i + 1));
            current.setOnClickListener(view -> {
                if(chosenAvatar != null) {
                    if (current.getId() == chosenAvatar.getId()) {
                        current.setBackgroundResource(R.drawable.transparent);
                        chosenAvatar = null;
                    }
                    else{
                        chosenAvatar.setBackgroundResource(R.drawable.transparent);
                        current.setBackgroundResource(R.drawable.image_border);
                        chosenAvatar = current;
                    }
                }
                else{
                    current.setBackgroundResource(R.drawable.image_border);
                    chosenAvatar = current;
                }
            });

        }
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
        linear_avaters = findViewById(R.id.linear_avatars);
        fab_return = findViewById(R.id.fab_return);

    }

    private void init() {

        sign_up_BTN_sign_up.setOnClickListener(view -> {
            if(callInProgress)
                return;

            if (!isValidUserName()) {
                MyServices.getInstance().makeToast("Username cannot be empty! Try again");
                return;
            }
            if (!isValidEmail()) {
                MyServices.getInstance().makeToast("Invalid Email! Try again");
                return;
            }
            if (!isValidPassword()) {
                MyServices.getInstance().makeToast("Password cannot be empty! Try again");
                return;
            }
            if(chosenAvatar == null){
                MyServices.getInstance().makeToast("Must choose an avatar!");
                return;
            }
            callInProgress = true;
            DataManager.getDataManager().setActiveCallBack(loginCallBack);
            NewUserBoundary newUserBoundary = new NewUserBoundary();
            newUserBoundary.setAvatar(avatarsIdNameMap.get(chosenAvatar.getId()));
            newUserBoundary.setRole(UserRole.MANAGER);
            newUserBoundary.setUsername(sign_up_EDT_user_name.getText().toString());
            newUserBoundary.setEmail(sign_up_EDT_email.getText().toString());
            ServerCommunicator.getInstance().createUser(newUserBoundary);

            //TODO: find indication that sign up was successful. if successful then finish, else Toast a message.
            finish();
        });

        fab_return.setOnClickListener(view -> finish());
    }

    private boolean isValidEmail() {
        String email = sign_up_EDT_email.getText().toString();
        //Regular Expression
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword() {
        return !sign_up_EDT_password.getText().toString().isEmpty();
    }

    private boolean isValidUserName()
    {
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

