package com.ingsw.backend.Model.DTO;

import java.util.Date;

public class OrderDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private Integer tableId;

    //ATTRIBUTES
    private Double price;

    private Date date;

    // -------------------------------------------------

    //CONSTRUCTORS
    public OrderDTO() {
    }

    public OrderDTO(Integer id, Integer tableId, Double price, Date date) {
        this.id = id;
        this.tableId = tableId;
        this.price = price;
        this.date = date;
    }

    // -------------------------------------------------


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

    public void setPrice(Double price) {
        this.price = price;
    }
}
