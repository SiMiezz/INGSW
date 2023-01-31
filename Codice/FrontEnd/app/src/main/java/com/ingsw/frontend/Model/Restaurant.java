package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("name")
    private String name;

    //ATTRIBUTES
    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("locality")
    private String locality;

    @Expose
    @SerializedName("touristic")
    private boolean touristic;

    //CONSTRUCTORS
    public Restaurant() {
    }

    public Restaurant(String name, String description, String locality, boolean touristic) {
        this.name = name;
        this.description = description;
        this.locality = locality;
        this.touristic = touristic;
    }

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
