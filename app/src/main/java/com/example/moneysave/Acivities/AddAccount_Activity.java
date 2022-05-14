package com.example.moneysave.Acivities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddAccount_Activity extends AppCompatActivity {
    private EditText enter_account_name;
    private TextInputEditText food_LBL_money;
    private TextInputEditText leisure_and_recreation_LBL_money;
    private TextInputEditText car_LBL_money;
    private TextInputEditText apartment_LBL_money;
    private TextInputEditText clothing_and_footwear_LBL_money;
    private TextInputEditText various_expenses_LBL_money;
    private MaterialButton submit_add_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        InitViews();
        InitAddAccountButton();
    }

    private void InitViews(){
        enter_account_name = findViewById(R.id.enter_account_name);
        food_LBL_money = findViewById(R.id.food_LBL_money);
        leisure_and_recreation_LBL_money = findViewById(R.id.leisure_and_recreation_LBL_money);
        car_LBL_money = findViewById(R.id.car_LBL_money);
        apartment_LBL_money = findViewById(R.id.apartment_LBL_money);
        clothing_and_footwear_LBL_money = findViewById(R.id.clothing_and_footwear_LBL_money);
        various_expenses_LBL_money = findViewById(R.id.various_expenses_LBL_money);

    }

    private void InitAddAccountButton(){
        submit_add_account = findViewById(R.id.submit_add_account);
        submit_add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account newAccount = addAccount(); // Unchecked!!
                //TODO: Connect all objects and data to the database to continue functionality
                backToMyAccounts();
            }
        });
    }

    public Account addAccount(){
        Account newAccount = new Account(enter_account_name.getText().toString());
        newAccount.InitCategoriesList(Integer.valueOf(food_LBL_money.getText().toString()) ,Integer.valueOf(leisure_and_recreation_LBL_money.getText().toString()),Integer.valueOf(car_LBL_money.getText().toString()),Integer.valueOf(apartment_LBL_money.getText().toString()),Integer.valueOf(clothing_and_footwear_LBL_money.getText().toString()),Integer.valueOf(various_expenses_LBL_money.getText().toString()));
        return newAccount;
    }

    private void backToMyAccounts(){
        Intent intent = new Intent(this, MyAccountsActivity.class);
        startActivity(intent);
    }
}