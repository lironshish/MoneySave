package com.example.moneysave.Adapter;


import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.R;
import com.google.android.material.button.MaterialButton;

public class BankAccount_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class BankAccountHolder extends RecyclerView.ViewHolder {

        MaterialButton bank_BTN_distribution;
        MaterialButton bank_BTN_delete;


//        bank_BTN_distribution =findViewById(R.id.bank_BTN_distribution);
//        bank_BTN_delete =findViewById(R.id.bank_BTN_delete);
        public BankAccountHolder(@NonNull View itemView) {

            super(itemView);
        }
    }
}