package com.tana.foodapp.Model;

public class Dishes {
    private String image_url;

    public Dishes() {
    }

    public Dishes(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
