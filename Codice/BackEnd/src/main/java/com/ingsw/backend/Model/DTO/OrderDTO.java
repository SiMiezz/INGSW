package com.ingsw.backend.Model.DTO;

import com.ingsw.backend.Model.Element;

import java.util.List;

public class OrderDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private Integer tableId;

    private List<ElementDTO> elements;

    //ATTRIBUTES
    private Double price;

    private String datecreate;

    // -------------------------------------------------

    //CONSTRUCTORS
    public OrderDTO() {
    }

    public OrderDTO(Integer id, Integer tableId, List<ElementDTO> elements, Double price, String datecreate) {
        this.id = id;
        this.tableId = tableId;
        this.elements = elements;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public List<ElementDTO> getElements() {
        return elements;
    }

    public void setElements(List<ElementDTO> elements) {
        this.elements = elements;
    }
}
