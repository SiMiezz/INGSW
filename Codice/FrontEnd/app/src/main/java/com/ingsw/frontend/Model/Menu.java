package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("id")
    private Integer id;

    // ATTRIBUTES
    @Expose
    @SerializedName("qrCode")
    private String qrCode;

    //REFERENCES
    @Expose
    @SerializedName("restaurantName")
    private String restaurantName;

    //CONSTRUCTORS
    public Menu() {
    }

    public Menu(Integer id, String qrCode, String restaurantName) {
        this.id = id;
        this.qrCode = qrCode;
        this.restaurantName = restaurantName;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
