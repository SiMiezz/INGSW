package com.ingsw.backend.Model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    //PRIMARY KEY
    @Id
    @Column(name = "name", length = 50)
    private String name;


    //REFERENCES
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Element> elementList = new ArrayList<>();

    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="menu_code", referencedColumnName = "qrCode")
    private Menu menu;

    // -------------------------------------------------

    //CONSTRUCTORS
    public Category() {
    }

    public Category(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    // -------------------------------------------------


}
