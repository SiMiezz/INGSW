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
    @SerializedName("menu_id")
    private Integer menuId;

    //CONSTRUCTORS


    public Category(Integer id, String name, Integer menuId) {
        this.id = id;
        this.name = name;
        this.menuId = menuId;
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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    private Boolean checked = Boolean.valueOf(false);

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
