package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ingsw.backend.Model.Enumerations.User_Type;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user")
public class User {

    //PRIMARY KEY
    @Id
    @Column(name = "idUser", length = 50)
    private String id;


    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY) //foreign key
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="restaurant_name", referencedColumnName = "name")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private Restaurant restaurant;


    //ATTRIBUTES
    @Column(name = "password", length = 50, nullable = false)
    private String pwd;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "surname", length = 50)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "job", nullable = false)
    private User_Type job;



    // -------------------------------------------------


    //CONSTRUCTORS
    public User() {
    }

    public User(String id, String pwd, String name, String surname, User_Type job) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.job = job;
    }

    public User(String id, String pwd, String name, String surname, User_Type job, Restaurant restaurant) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.restaurant = restaurant;
    }

    // -------------------------------------------------


    //GETTERS AND SETTERS


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User_Type getJob() {
        return job;
    }

    public void setJob(User_Type job) {
        this.job = job;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }



    // -------------------------------------------------


}
