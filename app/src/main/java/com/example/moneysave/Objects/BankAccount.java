package com.example.moneysave.Objects;

import java.util.ArrayList;

public class BankAccount {

    private String name = " ";
    private float expenses = 0;
    private float revenues = 0;
    private boolean isManual = true;
    private ArrayList<Detail> details= new ArrayList<>();

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }

    public BankAccount() {
    }

    public String getName() {
        return name;
    }

    public BankAccount setName(String name) {
        this.name = name;
        return this;
    }

    public float getExpenses() {
        return expenses;
    }

    public BankAccount setExpenses(float expenses) {
        this.expenses = expenses;
        return this;
    }

    public float getRevenues() {
        return revenues;
    }

    public BankAccount setRevenues(float revenues) {
        this.revenues = revenues;
        return this;
    }

    public boolean isManual() {
        return isManual;
    }

    public BankAccount setManual(boolean manual) {
        isManual = manual;
        return this;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", expenses=" + expenses +
                ", revenues=" + revenues +
                ", isManual=" + isManual +
                ", details=" + details +
                '}';
    }
    public float[] myInAndOut(){
        float in = 0;
        float out = 0;
        for (int i = 0; i < details.size(); i++) {
            if(details.get(i).isRevenue())
                in+= details.get(i).getAmount();
            else
                out+= details.get(i).getAmount();
        }
        float[] ret={in , out , in-out};
        return ret;

    }
}
