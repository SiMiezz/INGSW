package com.ingsw.frontend.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Allergen {

    //PRIMARY KEY
    @SerializedName("name")
    private String name;

    //REFERENCES
    @SerializedName("element_List")
    private List<Element> elementList = new ArrayList<>();

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

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }
}
