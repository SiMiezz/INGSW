package com.ingsw.backend.Model.DTO;

public class RestaurantDTO {

    //PRIMARY KEY
    private String name;

    //ATTRIBUTES
    private String description;

    private String locality;

    private int tables;

    private int seats;

    private boolean touristic;


    // -------------------------------------------------

    //CONSTRUCTORS
    public RestaurantDTO() {
    }

    public RestaurantDTO(String name, String description, String locality, int tables, int seats, boolean touristic) {
        this.name = name;
        this.description = description;
        this.locality = locality;
        this.tables = tables;
        this.seats = seats;
        this.touristic = touristic;
    }


    // -------------------------------------------------


    //GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isTouristic() {
        return touristic;
    }

    public void setTouristic(boolean touristic) {
        this.touristic = touristic;
    }
}
