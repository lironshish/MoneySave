package com.example.moneysave.Objects;

public class Goal {

    private String name;
    private int moneyPerMonth = 0;
    private int moneyWested = 0;
    private String image;


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
}
