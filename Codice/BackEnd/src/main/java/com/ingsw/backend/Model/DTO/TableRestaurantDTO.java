package com.ingsw.backend.Model.DTO;

public class TableRestaurantDTO {

    //PRIMARY KEY
    private Integer id;


    //REFERENCES
    private String restaurantName;


    //ATTRIBUTES
    private int seats;

    private boolean free;


    // -------------------------------------------------

    //CONSTRUCTORS
    public TableRestaurantDTO() {
    }

    public TableRestaurantDTO(Integer id, String restaurantName, int seats, boolean free) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.seats = seats;
        this.free = free;
    }

    // -------------------------------------------------


    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
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
