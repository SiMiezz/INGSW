package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    //PRIMARY KEY
    @Expose
    @SerializedName("idOrder")
    private Integer id;

    //ATTRIBUTES
    @Expose
    @SerializedName("price")
    private double price;

    @Expose
    @SerializedName("date")
    private Date date;

    //REFERENCES
    @Expose
    @SerializedName("table_id")
    private Integer tableId;

    //CONSTRUCTORS
    public Order() {
    }

    public Order(Integer id, double price, Date date, Integer tableId) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.tableId = tableId;
    }

    //GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
}
