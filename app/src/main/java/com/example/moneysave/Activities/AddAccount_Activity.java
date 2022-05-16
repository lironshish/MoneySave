package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.moneysave.Adapter.Category_Adapter;
import com.example.moneysave.Data;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class AddAccount_Activity extends AppCompatActivity {
    private TextInputEditText addAccount_EDT_name;
    private MaterialTextView category_LBL_title;
    private AppCompatImageView category_IMG_image;
    private TextInputEditText category_EDT_amount;

    private MaterialButton submit_add_account;
    private RecyclerView category_LST_items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        InitViews();
        initAdapter();
        InitAddAccountButton();
    }

    private void InitViews() {

        category_LBL_title = findViewById(R.id.category_LBL_title);
        category_IMG_image = findViewById(R.id.category_IMG_image);
        category_LST_items = findViewById(R.id.category_LST_items);
        addAccount_EDT_name = findViewById(R.id.addAccount_EDT_name);
        category_EDT_amount = findViewById(R.id.category_EDT_amount);

    }

    private void InitAddAccountButton() {
        submit_add_account = findViewById(R.id.submit_add_account);
        submit_add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAccount(); // Unchecked!!
                //TODO: Connect all objects and data to the database to continue functionality
                backToMyAccounts();
            }
        });
    }

    public void addAccount() {
        Account newAccount = new Account(category_LBL_title.getText().toString());

        for (Goal category : newAccount.getCategories()) {
            for (int i = 0; i < 7; i++) {
                newAccount.getCategories().get(i).setMoneyPerMonth(Integer.valueOf(addAccount_EDT_name.getText().toString()));
            }
        }
        Log.d("pttt", newAccount.getCategories().toString());
        Toast.makeText(AddAccount_Activity.this, newAccount.getName() + " account saved", Toast.LENGTH_SHORT).show();

        //send the newAccount to DB
    }

    private void backToMyAccounts() {
        Intent intent = new Intent(this, MyAccountsActivity.class);
        startActivity(intent);
    }

    private void initAdapter(){

        ArrayList<Goal> categories = Data.generateCategories();
        Category_Adapter category_adapter = new Category_Adapter(this, categories);
        category_LST_items.setLayoutManager(new LinearLayoutManager(this));
        category_LST_items.setHasFixedSize(true);
        category_LST_items.setAdapter(category_adapter);


    }

}