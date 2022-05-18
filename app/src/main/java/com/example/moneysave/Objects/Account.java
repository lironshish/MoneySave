package com.example.moneysave.Objects;

import com.example.moneysave.Server.boundaries.User;

import java.util.ArrayList;

public class Account {
    private String name = "";
    private ArrayList<User> SharedWith = new ArrayList<User>();
    private ArrayList<Goal> Categories = new ArrayList<Goal>();


    public Account(){ }

    public Account(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getSharedWith() {
        return SharedWith;
    }

    public void setSharedWith(ArrayList<User> sharedWith) {
        SharedWith = sharedWith;
    }

    public ArrayList<Goal> getCategories() {
        return Categories;
    }


    public void setCategories(ArrayList<Goal> categories) {
        Categories = categories;
    }

    public void InitCategoriesList(int moneyPerFood, int moneyPerLeisureAndRecreation, int moneyPerCar, int moneyPerApartment, int moneyPerClothingAndFootwear, int moneyPerVariousExpenses){
        Categories.add(new Goal()
        .setName("Food").setMoneyPerMonth(moneyPerFood));
        Categories.add(new Goal()
                .setName("Leisure and recreation").setMoneyPerMonth(moneyPerLeisureAndRecreation));
        Categories.add(new Goal()
                .setName("Car").setMoneyPerMonth(moneyPerCar));
        Categories.add(new Goal()
                .setName("Apartment").setMoneyPerMonth(moneyPerApartment));
        Categories.add(new Goal()
                .setName("Clothing and footwear").setMoneyPerMonth(moneyPerClothingAndFootwear));
        Categories.add(new Goal()
                .setName("Various expenses").setMoneyPerMonth(moneyPerVariousExpenses));
    }


    //TODO: Add functions that pull and push data to the database

}
