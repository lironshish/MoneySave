package com.example.moneysave;

import com.example.moneysave.Objects.BankAccount;
import com.example.moneysave.Objects.Detail;
import com.example.moneysave.Objects.Goal;

import java.util.ArrayList;

public class Data {
    // TODO: 5/19/2022 add bank , remove bank
    // TODO: 5/19/2022 get banks
    // TODO: 5/19/2022 update bank
    // TODO: 5/19/2022 add goal
    // TODO: 5/19/2022 get goals


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


    public static ArrayList<Detail> generateDetails() {
        ArrayList<Detail> details = new ArrayList<>();
        details.add(new Detail().setDescription("electric bill").setAmount(100).setImage("ic_home").setCategory("HOME"));
    return details;
    }
}
