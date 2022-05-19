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
import com.example.moneysave.call_backs.CreateAndUpdateAccount;
import com.example.moneysave.call_backs.GetAccounts_callback;
import com.example.moneysave.tools.DataManager;
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
    private ArrayList<Goal> categories;

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
                addAccount();
                //TODO: Connect all objects and data to the database to continue functionality
                backToMyAccounts();
            }
        });
    }

    public void addAccount() {
        DataManager.getDataManager().setActiveCallBack(createAndUpdateAccount);
        Account newAccount = new Account(addAccount_EDT_name.getText().toString());
        DataManager.getDataManager().addAccount(newAccount);

        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).setMoneyPerMonth(100);
        }

    }

    private void backToMyAccounts() {
        Intent intent = new Intent(this, MyAccountsActivity.class);
        startActivity(intent);
        finish();
    }

    private void initAdapter() {

        categories = DataManager.getDataManager().generateCategories();
        Category_Adapter category_adapter = new Category_Adapter(this, categories);
        category_LST_items.setLayoutManager(new LinearLayoutManager(this));
        category_LST_items.setHasFixedSize(true);
        category_LST_items.setAdapter(category_adapter);
    }

    private CreateAndUpdateAccount createAndUpdateAccount = new CreateAndUpdateAccount() {
        @Override
        public void createOkUpdateBegin(Account account) {
            DataManager.getDataManager().addAccountCategories(account, categories);

            Toast.makeText(AddAccount_Activity.this, account.getName() + " account saved", Toast.LENGTH_SHORT).show();
            DataManager.getDataManager().setActiveCallBack(null);
        }
        @Override
        public void failed(int status_code) {

        }
        @Override
        public void empty() {

        }
    };
}