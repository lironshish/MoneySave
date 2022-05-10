package com.example.moneysave;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText main_EDT_email;
    private TextInputEditText main_EDT_password;
    private MaterialButton main_BTN_sign_in;
    private ExtendedFloatingActionButton main_FAB_sign_up;
    private Intent accounts_activity;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        bundle = new Bundle();
        accounts_activity = new Intent(this,AccountActivity.class);
        findViews();
        init();
    }

    private void findViews() {
        main_EDT_email = findViewById(R.id.main_EDT_email);
        main_EDT_password = findViewById(R.id.main_EDT_password);
        main_BTN_sign_in = findViewById(R.id.main_BTN_sign_in);
        main_FAB_sign_up = findViewById(R.id.main_FAB_sign_up);
    }
    private void init() {
        main_BTN_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(accounts_activity);
                //TODO: load and get in + bundle send the user
            }
        });
        main_FAB_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}

