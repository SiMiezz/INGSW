package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tablerestaurant")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class TableRestaurant implements Serializable {

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
    private List<Order> orderList = new ArrayList<>();


    //ATTRIBUTES
    @Column(name = "seats")
    private int seats;

    @Column(name = "free", nullable = false)
    private boolean free;


    // -------------------------------------------------

    //CONSTRUCTORS
    public TableRestaurant() {
    }

    public TableRestaurant(Integer id, Restaurant restaurant, int seats, boolean free) {
        this.id = id;
        this.restaurant = restaurant;
        this.seats = seats;
        this.free = free;
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

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

// -------------------------------------------------


}
