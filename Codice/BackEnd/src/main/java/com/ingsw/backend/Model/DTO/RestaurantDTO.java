package com.ingsw.backend.Model.DTO;

public class RestaurantDTO {

    //PRIMARY KEY
    private String name;

    //ATTRIBUTES
    private String description;

    private String locality;

    private boolean touristic;


    // -------------------------------------------------

    //CONSTRUCTORS
    public RestaurantDTO() {
    }

    public RestaurantDTO(String name, String description, String locality, boolean touristic) {
        this.name = name;
        this.description = description;
        this.locality = locality;
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

    public boolean isTouristic() {
        return touristic;
    }

    public void setTouristic(boolean touristic) {
        this.touristic = touristic;
    }
}
