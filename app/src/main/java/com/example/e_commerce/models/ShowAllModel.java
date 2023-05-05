package com.example.e_commerce.models;

public class ShowAllModel {

    String Description;
    String Name;
    String Popularity;
    int Price;
    String img_url;

    public ShowAllModel(String description, String name, String popularity, String img_url, int price) {
        this.Description = description;
        this.Name = name;
        this.Popularity = popularity;
        this.img_url = img_url;
        this.Price = price;
    }
    public ShowAllModel() {
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
