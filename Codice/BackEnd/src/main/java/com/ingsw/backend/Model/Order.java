package com.ingsw.backend.Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ordine")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Order implements Serializable {

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
    @JoinTable(name = "composed", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "element_id"))
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private List<Element> elementOrderList = new ArrayList<>();

    //ATTRIBUTES
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "datecreate", nullable = false)
    private Date datecreate;


    // -------------------------------------------------

    //CONSTRUCTORS
    public Order() {
    }

    public Order(Integer id, TableRestaurant table, double price, Date datecreate) {
        this.id = id;
        this.table = table;
        this.price = price;
        this.datecreate = datecreate;
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

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public TableRestaurant getTable() {
        return table;
    }

    public void setTable(TableRestaurant table) {
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
