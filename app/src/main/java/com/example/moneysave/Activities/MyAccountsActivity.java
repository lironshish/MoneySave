package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.moneysave.Adapter.Account_Adapter;
import com.example.moneysave.Data;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.R;
import com.example.moneysave.tools.DataManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MyAccountsActivity extends AppCompatActivity {

    private FloatingActionButton addAccount;
    private RecyclerView account_list;
    private TextView firstTexst;
    private View allAccounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accounts);

        InitButtons();


       if (DataManager.getDataManager().getMyAccounts().size()==0) {
           firstTexst.setText("Hello\n"+DataManager.getDataManager().getMyUser().getUsername()+"\nTo start using the app\nadd your first account");
            firstTexst.setVisibility(View.VISIBLE);
            Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
           firstTexst.startAnimation(aniFade);
       }
       else{
           firstTexst.setVisibility(View.INVISIBLE);
           allAccounts = findViewById(R.id.all_accounts);
           allAccounts.setVisibility(View.VISIBLE);
           initAdapter();

       }
    }

    private void initAdapter() {
        ArrayList<Account> accounts = DataManager.getDataManager().getMyAccounts();
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
                AlertDialog alertDialog = new AlertDialog.Builder(MyAccountsActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("warning")
                        .setMessage("Are you sure you want to remove this account?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DataManager.getDataManager().removeAccount(account);
                                initAdapter();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();


            }
        });
    }


    public void InitButtons(){

        account_list = findViewById(R.id.myAccounts_LST_accounts);
        addAccount = findViewById(R.id.account_BTN_Add);
        firstTexst = findViewById(R.id.panel_text);

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
        finish();
    }
}
