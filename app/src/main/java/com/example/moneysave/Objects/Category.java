package com.example.moneysave.Objects;

public class Category {

    private String name;
    private int moneyPerMonth = 0;

    public Category(){}

    public Category(String name, int moneyPerMonth) {
        setName(name);
        setMoneyPerMonth(moneyPerMonth);
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public int getMoneyPerMonth() {
        return moneyPerMonth;
    }

    public Category setMoneyPerMonth(int moneyPerMonth) {
        this.moneyPerMonth = moneyPerMonth;
        return this;
    }
}
