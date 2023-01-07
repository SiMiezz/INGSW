package com.ingsw.backend.Model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    //PRIMARY KEY
    @Id
    @Column(name = "name", length = 50)
    private String name;

    //REFERENCES
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  //EAGER carica la lista automaticamente quando viene caricato un ristorante, LAZY no (meglio farlo a mano)
    private List<User> userList = new ArrayList<>();                                        // restaurant è il nome dell'attributo dal lato dell'User

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TableRestaurant> tablesList = new ArrayList<>();

    @OneToOne(mappedBy = "restaurant")
    private Menu menu;


    //ATTRIBUTES
    @Column(name = "description")
    private String description;

    @Column(name = "locality", length = 50)
    private String locality;

    @Column(name = "tables", nullable = false)
    private int tables;

    @Column(name = "seats", nullable = false)
    private int seats;

    @Column(name = "touristic", nullable = false)
    private boolean touristic;





    // -------------------------------------------------

    //CONSTRUCTORS
    public Restaurant() {
    }

    public Restaurant(String name, String description, String locality, int tables, int seats, boolean touristic) {
        this.name = name;
        this.description = description;
        this.locality = locality;
        this.tables = tables;
        this.seats = seats;
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

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
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
