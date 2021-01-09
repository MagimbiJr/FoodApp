package com.tana.foodapp.Model;

public class PopFood {
    private String name, type, description, image_url, price;

    public PopFood() {
    }

    public PopFood(String name, String type, String description, String image_url, String price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
