package com.example.moneysave.Acivities;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class AccountActivity extends AppCompatActivity {

    private RecyclerView account_LST_AccountsBank;
    private RecyclerView account_LST_goals;
    private FloatingActionButton account_BTN_Add;
    private BottomAppBar account_BottomAppBar;
    private DrawerLayout drawer_layout;
    private NavigationView account_navigation;
    private ExtendedFloatingActionButton account_BTN_AddBankAccount;
    private ExtendedFloatingActionButton account_BTN_AddGoal;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

    }
}
