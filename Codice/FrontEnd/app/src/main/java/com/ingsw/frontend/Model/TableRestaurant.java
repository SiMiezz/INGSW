package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TableRestaurant {

    //PRIMARY KEY
    @Expose
    @SerializedName("idTable")
    private Integer id;

    //ATTRIBUTES
    @Expose
    @SerializedName("seats")
    private int seats;

    //REFERENCES
    @Expose
    @SerializedName("restaurant_name")
    private String restaurantName;

    //CONSTRUCTORS
    public TableRestaurant() {
    }

    public TableRestaurant(Integer id, int seats, String restaurantName) {
        this.id = id;
        this.seats = seats;
        this.restaurantName = restaurantName;
    }

    //GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
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
