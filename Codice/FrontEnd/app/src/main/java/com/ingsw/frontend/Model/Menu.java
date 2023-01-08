package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    //PRIMARY KEY
    @Expose
    @SerializedName("idMenu")
    private Integer id;

    // ATTRIBUTES
    @Expose
    @SerializedName("qrCode")
    private String qrCode;

    //REFERENCES
    @Expose
    @SerializedName("category_List")
    private List<Category> categoryList = new ArrayList<>();

    @Expose
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
