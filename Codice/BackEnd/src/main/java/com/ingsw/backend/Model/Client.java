package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //REFERENCES
    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="table_id", referencedColumnName = "idTable")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private TableRestaurant table;

    // -------------------------------------------------


    //CONSTRUCTORS
    public Client() {

    }

    public Client(Integer id) {
        this.id = id;
    }

    // -------------------------------------------------


    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TableRestaurant getTable() {
        return table;
    }

    public void setTables(TableRestaurant table) {
        this.table = table;
    }


    // -------------------------------------------------


}
