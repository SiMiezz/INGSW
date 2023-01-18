package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name", scope = Long.class)
public class Restaurant implements Serializable {

    //PRIMARY KEY
    @Id
    @Column(name = "name", length = 50)
    private String name;

    //REFERENCES
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TableRestaurant> tablesList = new ArrayList<>();

    @OneToOne(mappedBy = "restaurant")
    @JsonBackReference
    private Menu menu;


    //ATTRIBUTES
    @Column(name = "description")
    private String description;

    @Column(name = "locality", length = 50, nullable = false)
    private String locality;

    @Column(name = "touristic", nullable = false)
    private boolean touristic;


    // -------------------------------------------------

    //CONSTRUCTORS
    public Restaurant() {
    }

    public Restaurant(String name, String description, String locality, boolean touristic) {
        this.name = name;
        this.description = description;
        this.locality = locality;
        this.touristic = touristic;
    }


    // -------------------------------------------------


    //GETTERS AND SETTERS

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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public boolean isTouristic() {
        return touristic;
    }

    public void setTouristic(boolean touristic) {
        this.touristic = touristic;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<TableRestaurant> getTablesList() {
        return tablesList;
    }

    public void setTablesList(List<TableRestaurant> tablesList) {
        this.tablesList = tablesList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    // -------------------------------------------------


}
