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
    @OneToOne(mappedBy = "stats", cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_name", referencedColumnName = "name")
    private Restaurant restaurant;


}
