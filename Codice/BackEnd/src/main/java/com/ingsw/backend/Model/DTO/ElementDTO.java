package com.ingsw.backend.Model.DTO;

public class ElementDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private Integer categoryId;

    //ATTRIBUTES
    private String name;

    private String description;

    private Double price;

    private Boolean prePackaged;

    // -------------------------------------------------

    //CONSTRUCTORS

    public ElementDTO() {
    }

    public ElementDTO(Integer id, Integer categoryId, String name, String description, Double price, Boolean prePackaged) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.prePackaged = prePackaged;
    }

    // -------------------------------------------------


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

    public Boolean getPrePackaged() {
        return prePackaged;
    }
}
