package com.example.moneysave.Objects;

import com.example.moneysave.Server.ServerCommunicator;
import com.example.moneysave.Server.boundaries.CreatedBy;
import com.example.moneysave.Server.boundaries.InstanceBoundary;
import com.example.moneysave.Server.boundaries.UserId;
import com.example.moneysave.tools.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Account extends InstanceBoundary {

    public Account() {
        super();
    }

    public Account(String name) {
        this.setActive(true);
        this.setCreatedBy(new CreatedBy(DataManager.getDataManager().getMyUser().getUserId()));
        this.setType(UserDetails.class.getSimpleName());
        this.setName(name);
        this.setInstanceAttributes(new HashMap<>());
        this.getInstanceAttributes().put(DataManager.KEY_MY_USERS , new ArrayList<UserId>() );
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
    }

    public ArrayList<UserId> receive_myUsers() {
        return (ArrayList<UserId>) this.getInstanceAttributes().get(DataManager.KEY_MY_USERS);
    }

    public Account add_user(UserId myUser) {
        ((ArrayList<UserId>) this.getInstanceAttributes().get(DataManager.KEY_MY_USERS)).add(myUser);
        updateOnServer();
        return this;
    }

    public ArrayList<Goal> receive_myCategories() {
        return (ArrayList<Goal>) this.getInstanceAttributes().get(DataManager.KEY_MY_BANKS);
    }

    public Account update_myCategories(ArrayList<Goal> categories) {
        this.getInstanceAttributes().put(DataManager.KEY_MY_CATEGORIES , categories);
        return this;
    }

    public ArrayList<BankAccount> receive_myBankAccounts() {
        return (ArrayList<BankAccount>) this.getInstanceAttributes().get(DataManager.KEY_MY_BANKS);
    }

    public Account update_BankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.getInstanceAttributes().put(DataManager.KEY_MY_BANKS , bankAccounts);
        return this;
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

    public void remove_user(UserId userId) {
        ((ArrayList<UserId>) this.getInstanceAttributes().get(DataManager.KEY_MY_USERS)).remove(userId);
        updateOnServer();
    }
    public void updateOnServer(){
        ServerCommunicator.getInstance().updateInstanceDetails(this);
    }


    //TODO: Add functions that pull and push data to the database

}
