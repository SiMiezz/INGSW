package com.ingsw.backend.Model.DTO;

public class TableRestaurantDTO {

    //PRIMARY KEY
    private Integer id;


    //REFERENCES
    private String restaurantName;


    //ATTRIBUTES
    private int seats;


    // -------------------------------------------------

    //CONSTRUCTORS
    public TableRestaurantDTO() {
    }

    public TableRestaurantDTO(Integer id, String restaurantName, int seats) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.seats = seats;
    }

    // -------------------------------------------------


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
