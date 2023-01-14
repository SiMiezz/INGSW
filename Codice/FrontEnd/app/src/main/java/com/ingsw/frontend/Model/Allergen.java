package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Allergen implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("name")
    private String name;

    //CONSTRUCTORS

    public Allergen(String name) {
        this.name = name;
    }

    public Allergen() {
    }

    //GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
