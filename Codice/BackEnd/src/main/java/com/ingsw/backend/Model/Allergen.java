package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "allergen")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name", scope = Long.class)
public class Allergen implements Serializable {

    //PRIMARY KEY
    @Id
    @Column(name = "name", length = 50)
    private String name;

    //REFERENCES
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "contains", joinColumns = @JoinColumn(name = "allergen_name"), inverseJoinColumns = @JoinColumn(name = "element_id"))
    @JsonBackReference
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
