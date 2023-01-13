package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tablerestaurant")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class TableRestaurant {

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTable")
    private Integer id;


    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="restaurant_name", referencedColumnName = "name")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Client> clientList = new ArrayList<>();

    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orderList = new ArrayList<>();


    //ATTRIBUTES
    @Column(name = "seats")
    private int seats;


    // -------------------------------------------------

    //CONSTRUCTORS
    public TableRestaurant() {
    }

    public TableRestaurant(int id, int seats, Restaurant restaurant) {
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
