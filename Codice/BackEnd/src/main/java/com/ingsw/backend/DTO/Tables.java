package com.example.provaing.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tablesRestaurant")
public class Tables { //non può essere Table per notazione @Table che genera un errore

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="restaurant_name", referencedColumnName = "name")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "tables", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Client> clientList = new ArrayList<>();

    @OneToMany(mappedBy = "tables", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();


    //ATTRIBUTES
    @Column(name = "seats_number")
    private int seats;


    // -------------------------------------------------

    //CONSTRUCTORS
    public Tables() {
    }

    public Tables(int id, int seats, Restaurant restaurant) {
        this.id = id;
        this.seats = seats;
        this.restaurant = restaurant;
    }


    // -------------------------------------------------


    //GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

// -------------------------------------------------


}
