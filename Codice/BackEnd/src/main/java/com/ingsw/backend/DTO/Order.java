package com.example.provaing.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "order")
public class Order {

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // == auto_increment
    @Column(name = "id")
    private int id;

    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="tables_id", referencedColumnName = "id")
    private Tables tables;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "composed", joinColumns = @JoinColumn(name = "id_order"), inverseJoinColumns = @JoinColumn(name = "id_element"))
    private List<Element> elementOrderList = new ArrayList<>();

    //ATTRIBUTES
    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private Date date;




    // -------------------------------------------------

    //CONSTRUCTORS
    public Order() {
    }

    public Order(int id, double price, Date date, Tables tables) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.tables = tables;
    }


    // -------------------------------------------------


    //GETTERS AND SETTERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }

    public List<Element> getElementOrderList() {
        return elementOrderList;
    }

    public void setElementOrderListList(List<Element> elementOrderList) {
        this.elementOrderList = elementOrderList;
    }

    // -------------------------------------------------


}
