package com.example.moneysave.Objects;

import android.util.Log;

import java.util.ArrayList;

public class Goal {

    private String name;
    private double moneyPerMonth = 0;
    private double moneyWested = 0;
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

    public double getMoneyPerMonth() {
        return moneyPerMonth;
    }

    public Goal setMoneyPerMonth(double moneyPerMonth) {
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

    public double getMoneyWested() {
        return moneyWested;
    }

    public Goal setMoneyWested(double moneyWested) {
        this.moneyWested = moneyWested;
        return this;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }
    public void addDetails(Detail detail) {
        this.details.add(detail);
        Log.d("myLog",this.details.toString()+"");
    }

    public Goal setDetails(ArrayList<Detail> details) {
        this.details = details;
        return this;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "name='" + name + '\'' +
                ", moneyPerMonth=" + moneyPerMonth +
                ", moneyWested=" + moneyWested +
                ", image='" +
                ", details=" +
                '}';
    }
}
