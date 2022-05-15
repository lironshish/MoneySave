package com.example.moneysave.Adapter;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.Acivities.AccountActivity;
import com.example.moneysave.Objects.BankAccount;
import com.example.moneysave.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class BankAccount_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface BankAccountListener{
        void clicked(BankAccount bankAccount, int position);
        void deleteBankAccount(BankAccount bankAccount, int position);

    }
    private Activity activity;
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private BankAccountListener bankAccountListener;


    public BankAccount_Adapter(Activity activity, ArrayList<BankAccount> bankAccounts) {
        this.activity = activity;
        this.bankAccounts = bankAccounts;
    }

    public void setBankAccountListener(BankAccountListener bankAccountListener) {
        this.bankAccountListener = bankAccountListener;
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
        BankAccount bankAccount = getBankAccount(position);

        holder.bank_TXT_name.setText(bankAccount.getName());

    }

    @Override
    public int getItemCount() {
        return bankAccounts.size();
    }

    public BankAccount getBankAccount(int position){
        return bankAccounts.get(position);
    }

    class BankAccountHolder extends RecyclerView.ViewHolder {

       private MaterialButton bank_BTN_distribution;
       private MaterialButton bank_BTN_delete;
       private MaterialTextView bank_TXT_name;

        public BankAccountHolder(@NonNull View itemView) {
            super(itemView);

            bank_BTN_delete =itemView.findViewById(R.id.bank_BTN_delete);
            bank_TXT_name = itemView.findViewById(R.id.bank_TXT_name);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (bankAccountListener != null) {
                        bankAccountListener.clicked(getBankAccount(getAdapterPosition()), getAdapterPosition());
                    }
                }

            });

            bank_BTN_distribution.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (bankAccountListener != null) {
                        bankAccountListener.clicked(getBankAccount(getAdapterPosition()), getAdapterPosition());
                    }
                }

            });

            bank_BTN_delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (bankAccountListener != null) {
                        bankAccountListener.clicked(getBankAccount(getAdapterPosition()), getAdapterPosition());
                    }
                }

            });
        }
    }
}