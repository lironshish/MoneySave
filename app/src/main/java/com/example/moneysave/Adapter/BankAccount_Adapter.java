package com.example.moneysave.Adapter;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.Objects.BankAccount;
import com.example.moneysave.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class BankAccount_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount_Adapter(Activity activity, ArrayList<BankAccount> bankAccounts) {
        this.activity = activity;
        this.bankAccounts = bankAccounts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bank, parent, false);
        BankAccountHolder holder = new BankAccountHolder(view);
            return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final BankAccountHolder holder = (BankAccountHolder) viewHolder;
        BankAccount bankAccount = bankAccounts.get(position);

        holder.bank_TXT_name.setText(bankAccount.getName());

    }

    @Override
    public int getItemCount() {
        return bankAccounts.size();
    }

    class BankAccountHolder extends RecyclerView.ViewHolder {

       private MaterialButton bank_BTN_distribution;
       private MaterialButton bank_BTN_delete;
       private MaterialTextView bank_TXT_name;

        public BankAccountHolder(@NonNull View itemView) {
            super(itemView);

            bank_BTN_distribution =itemView.findViewById(R.id.bank_BTN_distribution);
            bank_BTN_delete =itemView.findViewById(R.id.bank_BTN_delete);
            bank_TXT_name = itemView.findViewById(R.id.bank_TXT_name);
        }
    }
}