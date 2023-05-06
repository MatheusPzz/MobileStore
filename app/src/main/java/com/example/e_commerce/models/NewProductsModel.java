package com.example.e_commerce.models;

import java.io.Serializable;

public class NewProductsModel implements Serializable {

    String Description;
    String Name;
    String Popularity;
    int Price;
    String img_url;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NewProductsModel() {
    }

    public NewProductsModel(String description, String name, String popularity, int price, String img_url, String type) {
        this.Description = description;
        this.Name = name;
        this.Popularity = popularity;
        this.Price = price;
        this.img_url = img_url;
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPopularity() {
        return Popularity;
    }

    public void setPopularity(String popularity) {
        Popularity = popularity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
