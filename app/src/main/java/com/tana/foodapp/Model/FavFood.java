package com.tana.foodapp.Model;

public class FavFood {
    private String name, amount, image_url;

    public FavFood() {
    }

    public FavFood(String name, String amount, String image_url) {
        this.name = name;
        this.amount = amount;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
