package com.ingsw.backend.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "idOrder")
    private Integer id;

    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="table_id", referencedColumnName = "idTable")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private TableRestaurant table;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "composed", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "element_id"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private List<Element> elementOrderList = new ArrayList<>();

    //ATTRIBUTES
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "date", nullable = false)
    private Date date;




    // -------------------------------------------------

    //CONSTRUCTORS
    public Order() {
    }

    public Order(int id, double price, Date date, TableRestaurant table) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.table = table;
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

    public TableRestaurant getTable() {
        return table;
    }

    public void setTables(TableRestaurant table) {
        this.table = table;
    }

    public List<Element> getElementOrderList() {
        return elementOrderList;
    }

    public void setElementOrderList(List<Element> elementOrderList) {
        this.elementOrderList = elementOrderList;
    }

    // -------------------------------------------------


}
