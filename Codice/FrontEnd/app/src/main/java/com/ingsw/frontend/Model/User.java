package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ingsw.frontend.Model.Enumerations.User_Type;

public class User {

    //PRIMARY KEY
    @Expose
    @SerializedName("idUser")
    private String id;

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
    @SerializedName("idUser")
    private Restaurant restaurant;

    //CONSTRUCTORS
    public User() {
    }

    public User(String id, String pwd, String name, String surname, User_Type job) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.job = job;
    }

    public User(String id, String pwd, String name, String surname, User_Type job, Restaurant restaurant) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.restaurant = restaurant;
    }

    //GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
