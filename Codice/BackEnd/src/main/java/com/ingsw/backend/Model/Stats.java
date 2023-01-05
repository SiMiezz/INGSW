package com.ingsw.backend.Model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "stats")
public class Stats {

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //REFERENCES
    @OneToOne
    @JoinColumn(name = "restaurant_name", referencedColumnName = "name")
    private Restaurant restaurant;

    public Stats(Integer id, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
    }

    public Stats() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
