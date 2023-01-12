package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;

import java.util.ArrayList;
import java.util.List;

public class Element {

    //PRIMARY KEY
    @Expose
    @SerializedName("idElement")
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
    private boolean prePackaged;

    @Expose
    @SerializedName("aliment")
    private Aliment_Type aliment;

    //REFERENCES
    @Expose
    @SerializedName("category_id")
    private Integer categoryId;

    //CONSTRUCTORS
    public Element() {
    }

    public Element(Integer id, String name, String description, Double price, boolean prePackaged, Aliment_Type aliment, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.prePackaged = prePackaged;
        this.aliment = aliment;
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

    public boolean isPrePackaged() {
        return prePackaged;
    }

    public void setPrePackaged(boolean prePackaged) {
        this.prePackaged = prePackaged;
    }

    public Aliment_Type getAliment() {
        return aliment;
    }

    public void setAliment(Aliment_Type aliment) {
        this.aliment = aliment;
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
