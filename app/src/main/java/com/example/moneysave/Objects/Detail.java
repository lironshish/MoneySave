package com.example.moneysave.Objects;

public class Detail {

    private String description;
    private String image;
    private float amount;
    private String category;

    public Detail() {
    }

    public Detail(String description, String image, int amount, String category) {
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

    public String getCategory() {
        return category;
    }

    public Detail setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                '}';
    }
}
