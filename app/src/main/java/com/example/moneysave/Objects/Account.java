package com.example.moneysave.Objects;

import java.util.ArrayList;

public class Account {
    private String name = "";
    private ArrayList<User> SharedWith = new ArrayList<User>();
    private ArrayList<Category> Categories = new ArrayList<Category>();


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

    public ArrayList<Category> getCategories() {
        return Categories;
    }


    public void setCategories(ArrayList<Category> categories) {
        Categories = categories;
    }

    public void InitCategoriesList(int moneyPerFood, int moneyPerLeisureAndRecreation, int moneyPerCar, int moneyPerApartment, int moneyPerClothingAndFootwear, int moneyPerVariousExpenses){
        Categories.add(new Category()
        .setName("Food").setMoneyPerMonth(moneyPerFood));
        Categories.add(new Category()
                .setName("Leisure and recreation").setMoneyPerMonth(moneyPerLeisureAndRecreation));
        Categories.add(new Category()
                .setName("Car").setMoneyPerMonth(moneyPerCar));
        Categories.add(new Category()
                .setName("Apartment").setMoneyPerMonth(moneyPerApartment));
        Categories.add(new Category()
                .setName("Clothing and footwear").setMoneyPerMonth(moneyPerClothingAndFootwear));
        Categories.add(new Category()
                .setName("Various expenses").setMoneyPerMonth(moneyPerVariousExpenses));
    }


    //TODO: Add functions that pull and push data to the database

}
