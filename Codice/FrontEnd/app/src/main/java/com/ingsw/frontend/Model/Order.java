package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("id")
    private Integer id;

    //ATTRIBUTES
    @Expose
    @SerializedName("price")
    private double price;

    @Expose
    @SerializedName("datecreate")
    private String datecreate;

    //REFERENCES
    @Expose
    @SerializedName("tableId")
    private Integer tableId;

    @Expose
    @SerializedName("elements")
    private List<Element> elements;

    //CONSTRUCTORS
    public Order() {
    }

    public Order(double price, String datecreate, Integer tableId, List<Element> elements) {
        this.price = price;
        this.datecreate = datecreate;
        this.tableId = tableId;
        this.elements = elements;
    }

    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    private Boolean checked = Boolean.valueOf(false);

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
