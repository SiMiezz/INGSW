package com.ingsw.backend.Model.DTO;

public class OrderDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private Integer tableId;

    //ATTRIBUTES
    private Double price;

    private String date;

    // -------------------------------------------------

    //CONSTRUCTORS
    public OrderDTO() {
    }

    public OrderDTO(Integer id, Integer tableId, Double price, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
