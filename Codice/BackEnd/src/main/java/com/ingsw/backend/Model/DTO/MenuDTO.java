package com.ingsw.backend.Model.DTO;

public class MenuDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private String restaurantName;

    //CONSTRUCTORS

    public MenuDTO() {
    }

    public MenuDTO(Integer id, String restaurantName, String qrCode) {
        this.id = id;
        this.restaurantName = restaurantName;
    }

    // -------------------------------------------------


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
