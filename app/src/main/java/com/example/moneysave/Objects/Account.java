package com.example.moneysave.Objects;

import android.util.Log;

import com.example.moneysave.Server.ServerCommunicator;
import com.example.moneysave.Server.boundaries.CreatedBy;
import com.example.moneysave.Server.boundaries.InstanceBoundary;
import com.example.moneysave.tools.DataManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class Account extends InstanceBoundary {

    public Account() {
        super();
    }

    public Account(String name) {
        this.setActive(true);
        this.setCreatedBy(new CreatedBy(DataManager.getDataManager().getMyUser().getUserId()));
        this.setType(Account.class.getSimpleName());
        this.setName(name);
        this.setInstanceAttributes(new HashMap<>());
        this.getInstanceAttributes().put(DataManager.KEY_MY_CATEGORIES ,  new ArrayList<Goal>());
        this.getInstanceAttributes().put(DataManager.KEY_MY_BANKS , new ArrayList<BankAccount>());
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

        String jsonGoal = new Gson().toJson( this.getInstanceAttributes().get(DataManager.KEY_MY_CATEGORIES));
        String jsonBank = new Gson().toJson( this.getInstanceAttributes().get(DataManager.KEY_MY_BANKS));

        TypeToken tokenGoal = new TypeToken<ArrayList<Goal>>() {};
        TypeToken tokenBank = new TypeToken<ArrayList<BankAccount>>() {};

        this.getInstanceAttributes().put(DataManager.KEY_MY_CATEGORIES ,  new Gson().fromJson(jsonGoal , tokenGoal.getType()));
        this.getInstanceAttributes().put(DataManager.KEY_MY_BANKS , new Gson().fromJson(jsonBank , tokenBank.getType()));

    }


    public ArrayList<Goal> receive_myCategories() {
        return (ArrayList<Goal>) this.getInstanceAttributes().get(DataManager.KEY_MY_CATEGORIES);
    }

    public Account update_myCategories(ArrayList<Goal> categories) {
        this.getInstanceAttributes().put(DataManager.KEY_MY_CATEGORIES , categories);
        updateOnServer();
        return this;
    }
    public Account addCategory(Goal category) {
        ((ArrayList<Goal>) this.getInstanceAttributes().get(DataManager.KEY_MY_CATEGORIES)).add(category);
        updateOnServer();
        return this;
    }
    public Account removeCategory(Goal category) {
        ((ArrayList<Goal>) this.getInstanceAttributes().get(DataManager.KEY_MY_CATEGORIES)).remove(category);
        updateOnServer();
        return this;
    }

    public ArrayList<BankAccount> receive_myBankAccounts() {
        return (ArrayList<BankAccount>) this.getInstanceAttributes().get(DataManager.KEY_MY_BANKS);
    }
    public void addBank(BankAccount bankAccount) {
        ((ArrayList<BankAccount>) this.getInstanceAttributes().get(DataManager.KEY_MY_BANKS)).add(bankAccount);
        updateOnServer();
    }
    public void removeBank(BankAccount bankAccount) {
        ((ArrayList<BankAccount>) this.getInstanceAttributes().get(DataManager.KEY_MY_BANKS)).remove(bankAccount);
        updateOnServer();
    }


    public void InitCategoriesList(int moneyPerFood, int moneyPerLeisureAndRecreation, int moneyPerCar, int moneyPerApartment, int moneyPerClothingAndFootwear, int moneyPerVariousExpenses){
        ArrayList<Goal> categories =  receive_myCategories();
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

    public void updateOnServer(){
        ServerCommunicator.getInstance().updateInstanceDetails(
                this.getInstanceId().getDomain(),
                this.getInstanceId().getId(),
                DataManager.getDataManager().getMyUser().getUserId().getDomain(),
                DataManager.getDataManager().getMyUser().getUserId().getEmail(),
                this);
    }

    public void addDetail(Goal category, Detail detail) {
        ArrayList<Goal> myCategories =  receive_myCategories();
        if(myCategories.contains(category)) {
            category.addDetails(detail);
            Log.d("myLog",category.toString());
            updateOnServer();
        }

    }


    //TODO: Add functions that pull and push data to the database

}
