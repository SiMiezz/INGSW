package com.ingsw.frontend.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    //PRIMARY KEY
    @SerializedName("idMenu")
    private Integer id;

    // ATTRIBUTES
    @SerializedName("qrCode")
    private String qrCode;

    //REFERENCES
    @SerializedName("category_List")
    private List<Category> categoryList = new ArrayList<>();

    @SerializedName("restaurant_name")
    private Restaurant restaurant;

    //CONSTRUCTORS
    public Menu() {
    }

    public Menu(Integer id, Restaurant restaurant, String qrCode) {
        this.id = id;
        this.restaurant = restaurant;
        this.qrCode = qrCode;
    }

    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
