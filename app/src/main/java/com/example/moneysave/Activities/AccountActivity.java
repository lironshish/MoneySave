package com.example.moneysave.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.Adapter.BankAccount_Adapter;
import com.example.moneysave.Adapter.Goal_Adapter;
import com.example.moneysave.Data;
import com.example.moneysave.Objects.BankAccount;
import com.example.moneysave.Objects.Goal;
import com.example.moneysave.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {

    private RecyclerView account_LST_AccountsBank;
    private RecyclerView account_LST_goals;

    private FloatingActionButton account_FBT_Add;
    private BottomAppBar account_BottomAppBar;
    private DrawerLayout drawer_layout;
    private NavigationView account_navigation;
    private MaterialToolbar toolbar;
    private ExtendedFloatingActionButton account_BTN_AddBankAccount;
    private ExtendedFloatingActionButton account_BTN_AddGoal;
    private ExtendedFloatingActionButton account_BTN_AddManualBank;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private LinearLayout account_FBTmenu;

    private TextInputEditText popup_LBL_accountName;
    private MaterialButton popup_BTN_save;
    String accountName;


    private boolean isFBTOpen = false;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity1);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("MY ACCOUNT");
        //toolbar.setTitleTextColor(Integer.parseInt("#FFA726"));
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });


        findView();
        initButton();
        closeFBT();
        initAdapters();


    }

    private void initButton() {
        account_FBT_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFBTOpen) {
                    showFBT();
                } else {
                    closeFBT();
                }
            }
        });

        account_BTN_AddBankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, AddBankActivity.class));
            }
        });

        account_BTN_AddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, AddGoalActivity.class));
            }
        });
        account_BTN_AddManualBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v);
                closeFBT();
            }
        });


        account_navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_addBankAccount:
                        startActivity(new Intent(AccountActivity.this, AddBankActivity.class));
                        drawer_layout.closeDrawer(GravityCompat.START);
                    case R.id.nav_addGoal:
                        startActivity(new Intent(AccountActivity.this, AddGoalActivity.class));
                        drawer_layout.closeDrawer(GravityCompat.START);
                    case R.id.nav_share:
                        drawer_layout.closeDrawer(GravityCompat.START);
                    case R.id.nav_allAccounts:
                        drawer_layout.closeDrawer(GravityCompat.START);
                    case R.id.nav_Distribution:
                        drawer_layout.closeDrawer(GravityCompat.START);
                    case R.id.nav_logout:
                        drawer_layout.closeDrawer(GravityCompat.START);
                        AlertDialog alertDialog = new AlertDialog.Builder(AccountActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("warning")
                                .setMessage("Are you sure you want to delete the bank account?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();

                }
                return true;
            }

        });
    }

    private void findView() {
        drawer_layout = findViewById(R.id.account_DrawerLayout);
        account_navigation = findViewById(R.id.account_navigation);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.menu_Open, R.string.menu_Close);
        drawer_layout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        account_FBTmenu = findViewById(R.id.account_FBTmenu);
        account_FBT_Add = findViewById(R.id.account_FBT_Add);
        account_BTN_AddGoal = findViewById(R.id.account_BTN_AddGoal);
        account_BTN_AddBankAccount = findViewById(R.id.account_BTN_AddBankAccount);
        account_BTN_AddManualBank = findViewById(R.id.account_BTN_AddManualBank);
        account_LST_AccountsBank = findViewById(R.id.account_LST_AccountsBank);
        account_LST_goals = findViewById(R.id.account_LST_goals);
        popup_LBL_accountName = findViewById(R.id.popup_LBL_accountName);
        popup_BTN_save = findViewById(R.id.popup_BTN_save);

    }


    private void initAdapters(){
        ArrayList<BankAccount> bankAccounts = Data.generateBankAccounts();
        BankAccount_Adapter bankAccount_adapter = new BankAccount_Adapter(this,bankAccounts);
        account_LST_AccountsBank.setLayoutManager(new LinearLayoutManager(this));
        account_LST_AccountsBank.setHasFixedSize(true);
        account_LST_AccountsBank.setAdapter(bankAccount_adapter);

        bankAccount_adapter.setBankAccountListener(new BankAccount_Adapter.BankAccountListener() {
            @Override
            public void clicked(BankAccount bankAccount, int position) {
                Toast.makeText(AccountActivity.this, bankAccount.getName(), Toast.LENGTH_SHORT).show();
                //we need to decide if show now all the over vashav
            }


            @Override
            public void deleteBankAccount(BankAccount bankAccount, int position) {

                AlertDialog alertDialog = new AlertDialog.Builder(AccountActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("warning")
                        .setMessage("Are you sure you want to delete the bank account?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //add delete function
                                Toast.makeText(getApplicationContext(),bankAccount.getName()+" deleted",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                account_LST_AccountsBank.getAdapter().notifyDataSetChanged();
            }
        });

        ArrayList<Goal> goals = Data.generategoals();
        Goal_Adapter goal_adapter = new Goal_Adapter(this,goals);
        account_LST_goals.setLayoutManager(new LinearLayoutManager(this));
        account_LST_goals.setHasFixedSize(true);
        account_LST_goals.setAdapter(bankAccount_adapter);

        goal_adapter.setGoalListener(new Goal_Adapter.GoalListener(){
            @Override
            public void clicked(Goal goal, int position) {
                Toast.makeText(AccountActivity.this, goal.getName(), Toast.LENGTH_SHORT).show();
                //we need to decide if show now all the over vashav
            }

        });
    }
    private void showFBT() {
        isFBTOpen = true;
        TranslateAnimation animation = new TranslateAnimation(0, 0, account_FBTmenu.getHeight() + 1000, 0);
        animation.setDuration(500);
        animation.setFillAfter(true);
        account_FBTmenu.startAnimation(animation);
    }

    private void closeFBT() {
        isFBTOpen = false;
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, account_FBTmenu.getHeight() + 1000);
        animation.setDuration(500);
        animation.setFillAfter(true);
        account_FBTmenu.startAnimation(animation);

    }


    public void onButtonShowPopupWindowClick(View view) {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_window, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });


        //clickOnButton();
       // popupWindow.dismiss();

    }

    private void clickOnButton() {
        popup_BTN_save.setOnClickListener(view -> {
            accountName = popup_LBL_accountName.getText().toString();
            saveManuelAccount();
        });
    }
    private void saveManuelAccount() {
    }


}