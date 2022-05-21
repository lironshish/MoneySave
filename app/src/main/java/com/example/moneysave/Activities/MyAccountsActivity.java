package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.moneysave.Adapter.Account_Adapter;
import com.example.moneysave.Data;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.R;
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyAccountsActivity extends AppCompatActivity {

    private FloatingActionButton addAccount;
    private RecyclerView account_list;
    private TextView firstTexst;
    private MaterialButton popup_BTN_send;
    private TextInputEditText popup_LBL_email;
    private PopupWindow popupWindow;
    private String sendEmail = " ";
    private View allAccounts;
    private View currentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accounts);
        currentView = findViewById(android.R.id.content);


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
                onButtonShowPopupWindowClick(currentView , account);
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

            @Override
            public void clickName(Account account, int position) {
                DataManager.getDataManager().setActiveAccount(account);
                startActivity(new Intent(MyAccountsActivity.this, AccountActivity.class));
                account_list.getAdapter().notifyItemChanged(position);
                finish();
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


    public void onButtonShowPopupWindowClick(View view, Account accountToShare) {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_email, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popup_BTN_send =(MaterialButton) popupView.findViewById(R.id.popup_BTN_save);
        popup_LBL_email = (TextInputEditText) popupView.findViewById(R.id.popup_LBL_accountName);;
        popup_BTN_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidEmail()){
                    MyServices.getInstance().makeToast("Invalid email! try again.");
                    return;
                }
                sendEmail = popup_LBL_email.getText().toString();
                DataManager.getDataManager().share_Account(accountToShare , sendEmail);
                popupWindow.dismiss();
            }
        });
    }


    private boolean isValidEmail() {
        String email = popup_LBL_email.getText().toString();
        //Regular Expression
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
