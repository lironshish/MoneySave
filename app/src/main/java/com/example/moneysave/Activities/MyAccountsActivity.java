package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.moneysave.Adapter.Account_Adapter;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.R;
import com.example.moneysave.tools.DataManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MyAccountsActivity extends AppCompatActivity {

    private FloatingActionButton addAccount;
    private RecyclerView account_list;
    ArrayList<Account> accounts = new ArrayList<>();
    private TextView openingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accounts);
        InitButtons();

        // TODO: 19/05/2022
//        if (DataManager.getDataManager().getAllAccounts().length()==0) {
            Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            openingText.startAnimation(aniFade);
            openingText.setText("Hello\n" + DataManager.getDataManager().getMyUser().getUsername() + "\nTo start using the app\nadd your first account");
//        }

        // TODO: 19/05/2022
//        else {
//
//        }


        Account_Adapter accountAdapter = new Account_Adapter(this, accounts);
//        account_list.setLayoutManager(new LinearLayoutManager(this));
//        account_list.setHasFixedSize(true);
//        account_list.setAdapter(accountAdapter);

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
        addAccount = findViewById(R.id.account_BTN_Add);
        openingText = findViewById(R.id.panel_text);
        account_list = findViewById(R.id.accounts_list);

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
