package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "element")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Element implements Serializable {

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idElement")
    private Integer id;

    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="category_id", referencedColumnName = "idCategory")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private Category category;

    @ManyToMany(mappedBy = "elementOrderList", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Order> orderList = new ArrayList<>();

    @ManyToMany(mappedBy = "elementList", fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private List<Allergen> allergenList = new ArrayList<>();

    //ATTRIBUTES
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "translatename", length = 50)
    private String translateName;

    @Column(name = "description")
    private String description;

    @Column(name = "translatedescription")
    private String translateDescription;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "prepackaged", nullable = false)
    private Boolean prePackaged;

    // -------------------------------------------------

    //CONSTRUCTORS
    public Element() {
    }

    public Element(Integer id, Category category, String name, String translateName, String description, String translateDescription, Double price, Boolean prePackaged) {
        this.id = id;
        this.category = category;
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

    // -------------------------------------------------

}
