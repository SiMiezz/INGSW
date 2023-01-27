package com.ingsw.backend.Model.DTO;

public class OrderDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private Integer tableId;

    //ATTRIBUTES
    private Double price;

    private String datecreate;

    // -------------------------------------------------

    //CONSTRUCTORS
    public OrderDTO() {
    }

    public OrderDTO(Integer id, Integer tableId, Double price, String datecreate) {
        this.id = id;
        this.tableId = tableId;
        this.price = price;
        this.datecreate = datecreate;
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

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
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
