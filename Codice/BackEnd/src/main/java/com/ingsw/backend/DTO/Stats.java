package com.example.provaing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stats")
public class Stats {

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //REFERENCES
    //manca one to one con restaurant


}
