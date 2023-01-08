package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Category {

    //PRIMARY KEY
    @Expose
    @SerializedName("idCategory")
    private Integer id;

    // ATTRIBUTES
    @Expose
    @SerializedName("name")
    private String name;

    //REFERENCES
    @Expose
    @SerializedName("element_List")
    private List<Element> elementList = new ArrayList<>();

    @Expose
    @SerializedName("menu_id")
    private Menu menu;

    //CONSTRUCTORS

    public Category(Integer id, String name, Menu menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }

    public Category() {
    }

    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
