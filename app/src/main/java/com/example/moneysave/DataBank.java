package com.example.moneysave;

import com.example.moneysave.Objects.BankAccount;

import java.util.ArrayList;

public class DataBank {

    public static ArrayList<BankAccount> generateBankAccounts(){
        ArrayList<BankAccount>bankAccounts = new ArrayList<>();

        bankAccounts.add(new BankAccount()
                .setName("MIZRAHI").setExpenses(0).setRevenues(0).setManual(true));


        return bankAccounts;
    }
}
