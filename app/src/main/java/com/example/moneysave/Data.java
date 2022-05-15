package com.example.moneysave;

import com.example.moneysave.Objects.BankAccount;
import com.example.moneysave.Objects.Goal;

import java.util.ArrayList;

public class Data {

    public static ArrayList<BankAccount> generateBankAccounts(){
        ArrayList<BankAccount>bankAccounts = new ArrayList<>();

        bankAccounts.add(new BankAccount()
                .setName("MIZRAHI").setExpenses(0).setRevenues(0).setManual(true));


        return bankAccounts;
    }

    public static ArrayList<Goal> generategoals(){
        ArrayList<Goal>goals = new ArrayList<>();

        goals.add(new Goal()
                .setName("Food").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_add"));


        return goals;
    }
}
