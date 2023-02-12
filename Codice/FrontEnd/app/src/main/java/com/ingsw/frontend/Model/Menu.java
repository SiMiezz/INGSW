package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Menu implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("id")
    private Integer id;

    //REFERENCES
    @Expose
    @SerializedName("restaurantName")
    private String restaurantName;

    //CONSTRUCTORS
    public Menu() {
    }

    public Menu(Integer id, String qrCode, String restaurantName) {
        this.id = id;
        this.restaurantName = restaurantName;
    }

    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
