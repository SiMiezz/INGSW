package com.example.provaing.model;

import com.example.provaing.enumeration.Aliment_Type;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "element")
public class Element {

    //forse ci vuole id numerico come pk???

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="menu_code", referencedColumnName = "qr_code")
    private Menu menu;

    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="category_name", referencedColumnName = "name")
    private Category category;

    @ManyToMany(mappedBy = "elementOrderList", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();

    @ManyToMany(mappedBy = "elementList", fetch = FetchType.LAZY)
    private List<Allergen> allergenList = new ArrayList<>();

    //ATTRIBUTES
    @Column(name = "name", length = 50, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "prepackaged")
    private boolean prePackaged;

    @Column(name = "aliment_type")
    private Aliment_Type alimentType;



    // -------------------------------------------------

    //CONSTRUCTORS
    public Element() {
    }

    public Element(Integer id, String name, String description, Double price, boolean prePackaged, Aliment_Type alimentType, Menu menu, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.prePackaged = prePackaged;
        this.alimentType = alimentType;
        this.menu = menu;
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

    public Aliment_Type getAlimentType() {
        return alimentType;
    }

    public void setAlimentType(Aliment_Type alimentType) {
        this.alimentType = alimentType;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
