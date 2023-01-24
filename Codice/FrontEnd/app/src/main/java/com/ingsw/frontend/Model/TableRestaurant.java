package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableRestaurant implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("id")
    private Integer id;

    //ATTRIBUTES
    @Expose
    @SerializedName("seats")
    private Integer seats;

    //REFERENCES
    @Expose
    @SerializedName("restaurantName")
    private String restaurantName;

    //CONSTRUCTORS
    public TableRestaurant() {
    }

    public TableRestaurant(Integer id, Integer seats, String restaurantName) {
        this.id = id;
        this.seats = seats;
        this.restaurantName = restaurantName;
    }

    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
