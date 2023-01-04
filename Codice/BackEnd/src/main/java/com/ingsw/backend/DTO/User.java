package com.example.provaing.model;

import com.example.provaing.enumeration.User_Type;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user")//cambio nome della tabella se necessario (altrimenti viene chiamata User)
public class User {

    //PRIMARY KEY
    @Id
    @Column(name = "id")
    private String id;


    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY) //foreign key
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="restaurant_name", referencedColumnName = "name") //referencedColumn è la colonna della tabella ristorante a cui si fa riferimento
    private Restaurant restaurant;


    //ATTRIBUTES
    @Column(name = "password", length = 50)
    private String pwd;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "user_type")
    private User_Type userType;



    // -------------------------------------------------


    //CONSTRUCTORS
    public User() {
    }

    public User(String id, String pwd, String name, String surname, User_Type userType) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.userType = userType;
    }

    public User(String id, String pwd, String name, String surname, User_Type userType, Restaurant restaurant) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.surname = surname;
        this.userType = userType;
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

    public User_Type getEmployeeType() {
        return userType;
    }

    public void setEmployeeType(User_Type userType) {
        this.userType = userType;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }



    // -------------------------------------------------


}
