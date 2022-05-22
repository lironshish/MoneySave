package com.example.moneysave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moneysave.Adapter.Account_Adapter;
import com.example.moneysave.Adapter.Detail_Adapter;
import com.example.moneysave.Data;
import com.example.moneysave.Objects.Account;
import com.example.moneysave.Objects.Detail;
import com.example.moneysave.R;
import com.example.moneysave.tools.DataManager;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView category_LST_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        category_LST_items=findViewById(R.id.category_LST_items);
        initAdapter();
    }

    private void initAdapter() {
        ArrayList<Detail> details = DataManager.getDataManager().getActiveBankAccount().getDetails();
        Detail_Adapter detail_adapter = new Detail_Adapter(this, details);
        category_LST_items.setLayoutManager(new LinearLayoutManager(this));
        category_LST_items.setHasFixedSize(true);
        category_LST_items.setAdapter(detail_adapter);

    }


}