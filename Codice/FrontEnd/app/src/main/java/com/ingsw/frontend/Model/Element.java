package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Element implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("id")
    private Integer id;

    //ATTRIBUTES
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("price")
    private Double price;

    @Expose
    @SerializedName("prePackaged")
    private Boolean prePackaged;

    //REFERENCES
    @Expose
    @SerializedName("categoryId")
    private Integer categoryId;

    //CONSTRUCTORS
    public Element() {
    }

    public Element(String name, String description, Double price, Boolean prePackaged, Integer categoryId) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
        this.prePackaged = prePackaged;
        this.categoryId = categoryId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean isPrePackaged() {
        return prePackaged;
    }

    public void setPrePackaged(Boolean prePackaged) {
        this.prePackaged = prePackaged;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    private Boolean checked = Boolean.valueOf(false);

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
