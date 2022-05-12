package com.example.moneysave.Acivities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysave.R;
import com.example.moneysave.Server;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("myLog","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        RetrofitService retrofitService = new RetrofitService();
        Server api = retrofitService.getRetrofit().create(Server.class);
        api.get()
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        //Log.d("myLog", response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("myLog", t.getMessage());

                    }
                });

    }
}

