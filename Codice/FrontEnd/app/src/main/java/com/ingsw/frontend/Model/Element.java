package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
    @SerializedName("translateName")
    private String translateName;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("translateDescription")
    private String translateDescription;

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

    public Element(String name, String translateName, String description, String translateDescription, Double price, Boolean prePackaged, Integer categoryId) {
        this.name = name;
        this.translateName = translateName;
        this.description = description;
        this.translateDescription = translateDescription;
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

    public Boolean getPrePackaged() {
        return prePackaged;
    }

    public String getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String translateName) {
        this.translateName = translateName;
    }

    public String getTranslateDescription() {
        return translateDescription;
    }

    public void setTranslateDescription(String translateDescription) {
        this.translateDescription = translateDescription;
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
