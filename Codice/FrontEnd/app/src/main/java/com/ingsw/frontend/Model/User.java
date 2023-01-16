package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ingsw.frontend.Model.Enumerations.User_Type;

import java.io.Serializable;

public class User implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("email")
    private String email;

    //ATTRIBUTES
    @Expose
    @SerializedName("pwd")
    private String pwd;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("surname")
    private String surname;

    @Expose
    @SerializedName("job")
    private User_Type job;

    //REFERENCES
    @Expose
    @SerializedName("restaurantName")
    private String restaurantName;

    //CONSTRUCTORS
    public User() {
    }

    public User(String email, String pwd, String name, String surname, User_Type job, String restaurantName) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.restaurantName = restaurantName;
    }

    public User(String email, String pwd, String name, String surname, User_Type job) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.job = job;
    }

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

    private Boolean checked = Boolean.valueOf(false);

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
