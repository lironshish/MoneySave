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


    public static ArrayList<Goal> generateCategories(){
        ArrayList<Goal>categories = new ArrayList<>();

        categories.add(new Goal()
                .setName("FOOD").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_food"));

        categories.add(new Goal()
                .setName("RECREATIONS").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_recreations"));

        categories.add(new Goal()
                .setName("CAR").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_car"));

        categories.add(new Goal()
                .setName("HOME").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_home"));

        categories.add(new Goal()
                .setName("CLOTHING").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_clothing"));

        categories.add(new Goal()
                .setName("VARIOUS").setMoneyPerMonth(0).setMoneyWested(0).setImage("ic_various"));
        return categories;
    }
}
