package com.example.moneysave.Objects;

import com.example.moneysave.Server.boundaries.CreatedBy;
import com.example.moneysave.Server.boundaries.InstanceBoundary;
import com.example.moneysave.Server.boundaries.UserId;
import com.example.moneysave.tools.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Account extends InstanceBoundary {
    private ArrayList<Goal> categories = new ArrayList<Goal>();
    private ArrayList<UserId> myUsers = new ArrayList<UserId>();
    private ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    public Account() {
        super();
    }

    public Account(String name) {
        this.setActive(true);
        this.setCreatedBy(new CreatedBy(DataManager.getDataManager().getMyUser().getUserId()));
        this.setType(UserDetails.class.getSimpleName());
        this.setName(name);
        this.setInstanceAttributes(new HashMap<>());
        this.getInstanceAttributes().put(DataManager.KEY_MY_USERS , myUsers );
        this.getInstanceAttributes().put(DataManager.KEY_MY_CATEGORIES , categories);
        this.getInstanceAttributes().put(DataManager.KEY_MY_BANKS , bankAccounts);
    }


    public Account(InstanceBoundary instanceBoundary) {
        super(instanceBoundary.getInstanceId() ,
                instanceBoundary.getType() ,
                instanceBoundary.getName() ,
                instanceBoundary.getActive(),
                instanceBoundary.getCreatedTimestamp(),
                instanceBoundary.getCreatedBy(),
                instanceBoundary.getLocation(),
                instanceBoundary.getInstanceAttributes());
    }

    public ArrayList<UserId> getMyUsers() {
        return myUsers;
    }

    public Account setMyUsers(ArrayList<UserId> myUsers) {
        this.myUsers = myUsers;
        this.getInstanceAttributes().put(DataManager.KEY_MY_USERS , this.myUsers);
        return this;
    }

    public ArrayList<Goal> getCategories() {
        return categories;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public Account setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
        this.getInstanceAttributes().put(DataManager.KEY_MY_BANKS , this.bankAccounts);
        return this;
    }

    public void setCategories(ArrayList<Goal> categories) {
        this.categories = categories;
        this.getInstanceAttributes().put(DataManager.KEY_MY_CATEGORIES , this.categories);
    }

    public void InitCategoriesList(int moneyPerFood, int moneyPerLeisureAndRecreation, int moneyPerCar, int moneyPerApartment, int moneyPerClothingAndFootwear, int moneyPerVariousExpenses){
        categories.add(new Goal()
        .setName("Food").setMoneyPerMonth(moneyPerFood));
        categories.add(new Goal()
                .setName("Leisure and recreation").setMoneyPerMonth(moneyPerLeisureAndRecreation));
        categories.add(new Goal()
                .setName("Car").setMoneyPerMonth(moneyPerCar));
        categories.add(new Goal()
                .setName("Apartment").setMoneyPerMonth(moneyPerApartment));
        categories.add(new Goal()
                .setName("Clothing and footwear").setMoneyPerMonth(moneyPerClothingAndFootwear));
        categories.add(new Goal()
                .setName("Various expenses").setMoneyPerMonth(moneyPerVariousExpenses));
    }


    //TODO: Add functions that pull and push data to the database

}
