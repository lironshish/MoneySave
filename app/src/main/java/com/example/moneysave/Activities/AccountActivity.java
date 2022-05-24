package com.example.moneysave.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.moneysave.tools.DataManager;
import com.example.moneysave.tools.MyServices;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountActivity extends AppCompatActivity {

    private RecyclerView account_LST_AccountsBank;
    private RecyclerView account_LST_goals;
    private Activity activity;
    private CircleImageView naviHeader_IMG_user;
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
    private MaterialTextView expenses_title;
    private MaterialTextView Income_title;

    private TextInputEditText popup_LBL_accountName;
    private MaterialButton popup_BTN_save;
    String accountName = " ";
    private PopupWindow popupWindow;

    private boolean isFBTOpen = false;

    public AccountActivity() {
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);




        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("MY ACCOUNT");



        findView();
        initButton();
        closeFBT();
        initAdapters();

        float [] inAndOut = DataManager.getDataManager().getActiveAccount().myInAndOut();
        expenses_title.setText(inAndOut[1] +"");
        Income_title.setText(inAndOut[0] + "");
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
                MyServices.getInstance().makeToast("will be available on next update");

            }
        });

        account_BTN_AddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, AddGoalActivity.class));
                finish();
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
                        MyServices.getInstance().makeToast("will be available on next update");
                        drawer_layout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_addGoal:
                        startActivity(new Intent(AccountActivity.this, AddGoalActivity.class));
                        drawer_layout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_allAccounts:
                        startActivity(new Intent(AccountActivity.this, MyAccountsActivity.class));
                        drawer_layout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_Distribution:
                        startActivity(new Intent(AccountActivity.this, PieChartActivity.class));
                        // TODO: 5/21/2022 add all the information that needed for that 
                        drawer_layout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_logout:
                        drawer_layout.closeDrawer(GravityCompat.START);
                        AlertDialog alertDialog = new AlertDialog.Builder(AccountActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("warning")
                                .setMessage("Are you sure you want to logout?")
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
                        break;

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
        naviHeader_IMG_user = findViewById(R.id.naviHeader_IMG_user);
        expenses_title = findViewById(R.id.expenses_title);
        Income_title = findViewById(R.id.Income_title);


        toolbar.setOnClickListener(view -> {
            naviHeader_IMG_user = findViewById(R.id.naviHeader_IMG_user);
            String avatar = DataManager.getDataManager().getMyUser().getAvatar();
            switch (avatar){
                case "avatar1":
                    naviHeader_IMG_user.setImageResource(R.drawable.ic_avatar1);
                    break;
                case "avatar2":
                    naviHeader_IMG_user.setImageResource(R.drawable.ic_avatar2);
                    break;
                case "avatar3":
                    naviHeader_IMG_user.setImageResource(R.drawable.ic_avatar3);

                    break;
                case "avatar4":
                    naviHeader_IMG_user.setImageResource(R.drawable.ic_avatar4);

                    break;
                case "avatar5":
                    naviHeader_IMG_user.setImageResource(R.drawable.ic_avatar5);
                    break;

                default:
                    break;
            }
        });

    }


    private void initAdapters(){
        ArrayList<BankAccount> bankAccounts = DataManager.getDataManager().getAccountBanks(DataManager.getDataManager().getActiveAccount());

        BankAccount_Adapter bankAccount_adapter = new BankAccount_Adapter(this,bankAccounts);
        account_LST_AccountsBank.setLayoutManager(new LinearLayoutManager(this));
        account_LST_AccountsBank.setHasFixedSize(true);
        account_LST_AccountsBank.setAdapter(bankAccount_adapter);

        bankAccount_adapter.setBankAccountListener(new BankAccount_Adapter.BankAccountListener() {
            @Override
            public void clicked(BankAccount bankAccount, int position) {
                DataManager.getDataManager().setActiveBankAccount(bankAccount);
                startActivity(new Intent(AccountActivity.this,ManualBankActivity.class));
                finish();

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
                                DataManager.getDataManager().removeAccountBank(DataManager.getDataManager().getActiveAccount(), bankAccount);
                                account_LST_AccountsBank.getAdapter().notifyDataSetChanged();
                                account_LST_goals.getAdapter().notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(),bankAccount.getName()+" deleted",Toast.LENGTH_LONG).show();
                                float [] inAndOut = DataManager.getDataManager().getActiveAccount().myInAndOut();
                                expenses_title.setText(inAndOut[1] +"");
                                Income_title.setText(inAndOut[0] + "");
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

        ArrayList<Goal> goals = DataManager.getDataManager().getAccountCategories(DataManager.getDataManager().getActiveAccount());
        Goal_Adapter goal_adapter = new Goal_Adapter(this,goals);
        account_LST_goals.setLayoutManager(new LinearLayoutManager(this));
        account_LST_goals.setHasFixedSize(true);
        account_LST_goals.setAdapter(goal_adapter);

        goal_adapter.setGoalListener(new Goal_Adapter.GoalListener(){
            @Override
            public void clicked(Goal goal, int position) {
                Toast.makeText(AccountActivity.this, goal.getName(), Toast.LENGTH_SHORT).show();
                // TODO: 5/21/2022 add all details
            }

        });
        account_LST_goals.getAdapter().notifyDataSetChanged();
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
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popup_BTN_save =(MaterialButton) popupView.findViewById(R.id.popup_BTN_save);
        popup_LBL_accountName = (TextInputEditText) popupView.findViewById(R.id.popup_LBL_accountName);;


        popup_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountName = popup_LBL_accountName.getText().toString();
                popupWindow.dismiss();
                saveManuelAccount();
            }
        });


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent( AccountActivity.this, MyAccountsActivity.class));
        finish();
    }


    private void saveManuelAccount() {
        Toast.makeText(getApplicationContext(),accountName +" saved",Toast.LENGTH_LONG).show();
        DataManager.getDataManager().addAccountBank(DataManager.getDataManager().getActiveAccount(),
                new BankAccount().setName(accountName));
    }


}