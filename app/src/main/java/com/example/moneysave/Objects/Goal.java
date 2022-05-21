package com.example.moneysave.Objects;

import java.util.ArrayList;

public class Goal {

    private String name;
    private int moneyPerMonth = 0;
    private int moneyWested = 0;
    private String image;
    private ArrayList<Detail> details = new ArrayList<>();


    public Goal(){}

    public Goal(String name, int moneyPerMonth) {
        setName(name);
        setMoneyPerMonth(moneyPerMonth);
    }

    public String getName() {
        return name;
    }

    public Goal setName(String name) {
        this.name = name;
        return this;
    }

    public int getMoneyPerMonth() {
        return moneyPerMonth;
    }

    public Goal setMoneyPerMonth(int moneyPerMonth) {
        this.moneyPerMonth = moneyPerMonth;
        return this;
    }

    public String getImage() {
        return image;
    }


    public Goal setImage(String image) {
        this.image = image;
        return this;
    }

    public int getMoneyWested() {
        return moneyWested;
    }

    public Goal setMoneyWested(int moneyWested) {
        this.moneyWested = moneyWested;
        return this;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }
    public void addDetails(Detail detail) {
        this.details.add(detail);
    }

    public Goal setDetails(ArrayList<Detail> details) {
        this.details = details;
        return this;
    }
}
