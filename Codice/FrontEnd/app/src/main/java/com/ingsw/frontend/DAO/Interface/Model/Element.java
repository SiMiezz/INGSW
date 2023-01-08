package com.ingsw.frontend.DAO.Interface.Model;

import com.google.gson.annotations.SerializedName;
import com.ingsw.frontend.DAO.Interface.Model.Enumerations.Aliment_Type;

import java.util.ArrayList;
import java.util.List;

public class Element {

    //PRIMARY KEY
    @SerializedName("idElement")
    private Integer id;

    //ATTRIBUTES
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private Double price;

    @SerializedName("prePackaged")
    private boolean prePackaged;

    @SerializedName("aliment")
    private Aliment_Type aliment;

    //REFERENCES
    @SerializedName("category_id")
    private Category category;

    @SerializedName("order_List")
    private List<Order> orderList = new ArrayList<>();

    @SerializedName("allergen_List")
    private List<Allergen> allergenList = new ArrayList<>();

    //CONSTRUCTORS
    public Element() {
    }

    public Element(Integer id, String name, String description, Double price, boolean prePackaged, Aliment_Type aliment, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.prePackaged = prePackaged;
        this.aliment = aliment;
        this.category = category;
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

    public boolean isPrePackaged() {
        return prePackaged;
    }

    public void setPrePackaged(boolean prePackaged) {
        this.prePackaged = prePackaged;
    }

    public Aliment_Type getAliment() {
        return aliment;
    }

    public void setAliment(Aliment_Type aliment) {
        this.aliment = aliment;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Allergen> getAllergenList() {
        return allergenList;
    }

    public void setAllergenList(List<Allergen> allergenList) {
        this.allergenList = allergenList;
    }
}
