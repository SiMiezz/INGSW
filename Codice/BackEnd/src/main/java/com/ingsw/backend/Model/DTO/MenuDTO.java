package com.ingsw.backend.Model.DTO;


public class MenuDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private String restaurantName;

    // ATTRIBUTES
    private String qrCode;

    //CONSTRUCTORS

    public MenuDTO() {
    }

    public MenuDTO(Integer id, String restaurantName, String qrCode) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.qrCode = qrCode;
    }

    // -------------------------------------------------


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
