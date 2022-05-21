package com.example.moneysave.Activities;

import static java.lang.Integer.valueOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.moneysave.Adapter.BankAccount_Adapter;
import com.example.moneysave.Adapter.Category_Adapter;
import com.example.moneysave.Data;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.Objects.BankAccount;
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

        category_LST_items = findViewById(R.id.category_LST_items);
        addAccount_EDT_name = findViewById(R.id.addAccount_EDT_name);

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

        category_adapter.setCategoryListener(new Category_Adapter.CategoryListener() {
            @Override
            public void inputAmount(Goal category, int position) {
                categories.get(position).setMoneyPerMonth(category.getMoneyPerMonth());
                category_LST_items.getAdapter().notifyItemChanged(position);
            }

        });
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