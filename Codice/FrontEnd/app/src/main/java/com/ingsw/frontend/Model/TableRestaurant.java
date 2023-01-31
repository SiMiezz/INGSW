package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TableRestaurant implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("id")
    private Integer id;

    //ATTRIBUTES
    @Expose
    @SerializedName("seats")
    private Integer seats;

    @Expose
    @SerializedName("free")
    private boolean free;

    //REFERENCES
    @Expose
    @SerializedName("restaurantName")
    private String restaurantName;

    //CONSTRUCTORS
    public TableRestaurant() {
    }

    public TableRestaurant(Integer seats, boolean free, String restaurantName) {
        this.seats = seats;
        this.free = free;
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

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


}
