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
    private TableRestaurant table;

    @Expose
    @SerializedName("elementOrder_List")
    private List<Element> elementOrderList = new ArrayList<>();

    //CONSTRUCTORS
    public Order() {
    }

    public Order(int id, double price, Date date, TableRestaurant table) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.table = table;
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

    public TableRestaurant getTable() {
        return table;
    }

    public void setTables(TableRestaurant table) {
        this.table = table;
    }

    public List<Element> getElementOrderList() {
        return elementOrderList;
    }

    public void setElementOrderList(List<Element> elementOrderList) {
        this.elementOrderList = elementOrderList;
    }
}
