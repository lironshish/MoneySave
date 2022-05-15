package com.example.moneysave.Objects;

public class BankAccount {

    private String name = " ";
    private int expenses = 0;
    private int revenues = 0;
    private boolean isManual = true;

    public BankAccount() {
    }

    public String getName() {
        return name;
    }

    public BankAccount setName(String name) {
        this.name = name;
        return this;
    }

    public int getExpenses() {
        return expenses;
    }

    public BankAccount setExpenses(int expenses) {
        this.expenses = expenses;
        return this;
    }

    public int getRevenues() {
        return revenues;
    }

    public BankAccount setRevenues(int revenues) {
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
}
