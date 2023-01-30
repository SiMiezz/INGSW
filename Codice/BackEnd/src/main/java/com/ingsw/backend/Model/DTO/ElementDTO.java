package com.ingsw.backend.Model.DTO;

public class ElementDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private Integer categoryId;

    //ATTRIBUTES
    private String name;

    private String translateName;

    private String description;

    private String translateDescription;

    private Double price;

    private Boolean prePackaged;

    // -------------------------------------------------

    //CONSTRUCTORS

    public ElementDTO() {
    }

    public ElementDTO(Integer id, Integer categoryId, String name, String translateName, String description, String translateDescription, Double price, Boolean prePackaged) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.translateName = translateName;
        this.description = description;
        this.translateDescription = translateDescription;
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
}
