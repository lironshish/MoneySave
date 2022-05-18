package com.example.moneysave.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneysave.Objects.Account;
import com.example.moneysave.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Account_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public interface Accountlistener {
        void sharedWith(Account account, int position);
        void minus(Account account, int position);
    }

    private Activity activity;
    private ArrayList<Account> accounts = new ArrayList<>();
    private Accountlistener accountlistener;



    public Account_Adapter(Activity activity, ArrayList<Account> accounts){
        this.activity = activity;
        this.accounts = accounts;
    }

    public void setAccountlistener(Accountlistener accountlistener) {
        this.accountlistener = accountlistener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_account, parent, false);
        AccountHolder accountHolder = new AccountHolder(view);
        return accountHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final AccountHolder holder = (AccountHolder) viewHolder;
        Account account = getItem(position);

        holder.account_name.setText(account.getName());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }
    public Account getItem(int position) {
        return accounts.get(position);
    }


    class AccountHolder extends RecyclerView.ViewHolder {
        private MaterialTextView account_name;
        private MaterialButton minus_account;
        private MaterialButton share_account;
        public AccountHolder(View itemView) {
            super(itemView);
            account_name = itemView.findViewById(R.id.account_name);
            minus_account = itemView.findViewById(R.id.minus_account);
            share_account = itemView.findViewById(R.id.share_account);



            minus_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (accountlistener != null) {
                        accountlistener.minus(getItem(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });
            share_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //TODO: how to share account?
                }
            });

        }
    }
}
