package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.moneysave.Adapter.Account_Adapter;
import com.example.moneysave.Adapter.Detail_Adapter;
import com.example.moneysave.Data;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.Objects.Detail;
import com.example.moneysave.R;
import com.example.moneysave.tools.DataManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView category_LST_items;
    private FloatingActionButton fab_return;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        category_LST_items=findViewById(R.id.category_LST_items);
        findViews();
        initButtons();
        initAdapter();
    }

    private void findViews(){
        fab_return = findViewById(R.id.fab_return);
    }

    private void initButtons(){
    }
    private void initAdapter() {
        Log.d("mylog", "1111111111111111111111111111111");
        ArrayList<Detail> details = DataManager.getDataManager().getActiveBankAccount().getDetails();
        Log.d("mylog", "222222222");
        Detail_Adapter detail_adapter = new Detail_Adapter(this, details);
        Log.d("mylog", "33333333333");
        category_LST_items.setLayoutManager(new LinearLayoutManager(this));
        Log.d("mylog", "444444444");
        category_LST_items.setHasFixedSize(true);
        Log.d("mylog", "555555555");
        category_LST_items.setAdapter(detail_adapter);
        Log.d("mylog", "66666666");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent( DetailsActivity.this, ManualBankActivity.class));
        finish();
    }



}