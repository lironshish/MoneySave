package com.example.moneysave.Objects;

public class Goal {

    private String name;
    private int moneyPerMonth = 0;

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
}
