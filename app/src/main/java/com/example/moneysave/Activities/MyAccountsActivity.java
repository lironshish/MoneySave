package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moneysave.Adapter.Account_Adapter;
import com.example.moneysave.Data;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MyAccountsActivity extends AppCompatActivity {

    private FloatingActionButton addAccount;
    private RecyclerView account_list;
    ArrayList<Account> accounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accounts);

        InitButtons();
        initAdapter();
    }

    private void initAdapter() {
        ArrayList<Account> accounts = Data.generateAccounts();
        Account_Adapter accountAdapter = new Account_Adapter(this, accounts);
        account_list.setLayoutManager(new LinearLayoutManager(this));
        account_list.setHasFixedSize(true);
        account_list.setAdapter(accountAdapter);

        accountAdapter.setAccountlistener(new Account_Adapter.Accountlistener() {

            @Override
            public void sharedWith(Account account, int position) {
                //TODO
            }

            @Override
            public void minus(Account account, int position) {
                //TODO
            }
        });
    }


    public void InitButtons(){

        account_list = findViewById(R.id.myAccounts_LST_accounts);
        addAccount = findViewById(R.id.account_BTN_Add);
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceActivity();
            }
        });
    }

    private void replaceActivity(){
        Intent intent = new Intent(this, AddAccount_Activity.class);
        startActivity(intent);
    }
}
