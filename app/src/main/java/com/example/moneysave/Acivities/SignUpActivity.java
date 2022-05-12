package com.example.moneysave.Acivities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysave.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private MaterialToolbar sign_up_toolbar;
    private TextInputEditText sign_up_EDT_user_name;
    private TextInputEditText sign_up_EDT_email;
    private TextInputEditText sign_up_EDT_password;
    private MaterialButton sign_up_BTN_sign_up;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        findViews();
        init();
    }



    private void findViews() {
        //toolbar
        sign_up_toolbar = findViewById(R.id.signUp_toolbar);
        setSupportActionBar(sign_up_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sign_up_EDT_user_name = findViewById(R.id.sign_up_EDT_user_name);
        sign_up_EDT_email = findViewById(R.id.sign_up_EDT_email);
        sign_up_EDT_password = findViewById(R.id.sign_up_EDT_password);
        sign_up_BTN_sign_up = findViewById(R.id.sign_up_BTN_sign_up);
    }
    private void init() {
//        main_BTN_sign_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO: load and get in + bundle send the user
//            }
//        });
//        main_FAB_sign_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent myIntent = new Intent(this, SignUpActivity.class);
////                startActivity(myIntent);
////                finish();
//            }
//        });
//        main_BTN_sign_in.setOnClickListener(new View.OnClickListener() { //TODO: in sign up
//            @Override
//            public void onClick(View view) {
//                if(isEmailOk() ){ //TODO: add -> && isPasswordOk()
//                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"notttttt email address",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }

//    private boolean isEmailOk() {
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        return main_EDT_email.getText().toString().matches(emailPattern);
//    }
//    private boolean isPasswordOk() {
//        String passwordPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //TODO: change to password pattern
//        return main_EDT_password.getText().toString().matches(passwordPattern);
//    }

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
}

