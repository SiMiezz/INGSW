package com.example.provaing.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "allergen")
public class Allergen {

    //PRIMARY KEY
    @Id
    @Column(name = "name", length = 50)
    private String name;

    //REFERENCES
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "contains", joinColumns = @JoinColumn(name = "name_allergen"), inverseJoinColumns = @JoinColumn(name = "id_order"))
    private List<Element> elementList = new ArrayList<>();


    // -------------------------------------------------


    //CONSTRUCTORS
    public Allergen() {
    }

    public Allergen(String name) {
        this.name = name;
    }


    // -------------------------------------------------


    //GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }

    // -------------------------------------------------


}
