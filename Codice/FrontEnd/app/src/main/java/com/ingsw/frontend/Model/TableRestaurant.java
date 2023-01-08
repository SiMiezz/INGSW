package com.ingsw.frontend.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TableRestaurant {

    //PRIMARY KEY
    @SerializedName("idTable")
    private Integer id;

    //ATTRIBUTES
    @SerializedName("seats")
    private int seats;

    //REFERENCES
    @SerializedName("restaurant_name")
    private Restaurant restaurant;

    @SerializedName("client_list")
    private List<Client> clientList = new ArrayList<>();

    @SerializedName("order_list")
    private List<Order> orderList = new ArrayList<>();

    //CONSTRUCTORS
    public TableRestaurant() {
    }

    public TableRestaurant(int id, int seats, Restaurant restaurant) {
        this.id = id;
        this.seats = seats;
        this.restaurant = restaurant;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
