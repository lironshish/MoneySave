package com.example.moneysave.Objects;

public class Detail {

    private String description;
    private String image;
    private float amount;
    private Goal category;

    public Detail() {
    }

    public Detail(String description, String image, int amount, Goal category) {
        this.description = description;
        this.image = image;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public Detail setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Detail setImage(String image) {
        this.image = image;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public Detail setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public Goal getCategory() {
        return category;
    }

    public Detail setCategory(Goal category) {
        this.category = category;
        return this;
    }
}
