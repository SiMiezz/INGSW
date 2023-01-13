package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "element")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Element {

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
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private List<Order> orderList = new ArrayList<>();

    @ManyToMany(mappedBy = "elementList", fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private List<Allergen> allergenList = new ArrayList<>();

    //ATTRIBUTES
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "prepackaged", nullable = false)
    private boolean prePackaged;

    @Enumerated(EnumType.STRING)
    @Column(name = "aliment", nullable = false)
    private Aliment_Type aliment;



    // -------------------------------------------------

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

    // -------------------------------------------------


}
