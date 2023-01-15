package com.ingsw.backend.Model.DTO;

import com.ingsw.backend.Model.Enumerations.User_Type;

public class UserDTO {

    //PRIMARY KEY
    private String email;


    //REFERENCES
    private String restaurantName;


    //ATTRIBUTES
    private String pwd;

    private String name;

    private String surname;

    private User_Type job;



    // -------------------------------------------------


    //CONSTRUCTORS
    public UserDTO() {
    }

    public UserDTO(String email, String restaurantName, String pwd, String name, String surname, User_Type job) {
        this.email = email;
        this.restaurantName = restaurantName;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.job = job;
    }

    // -------------------------------------------------


    //GETTERS AND SETTERS


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User_Type getJob() {
        return job;
    }

    public void setJob(User_Type job) {
        this.job = job;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
