package com.ingsw.frontend.DAO.Interface.Model;

import com.google.gson.annotations.SerializedName;

public class Client {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    @SerializedName("table_id")
    private TableRestaurant table;

    //CONSTRUCTORS
    public Client() {

    }

    public Client(Integer id, TableRestaurant table) {
        this.id = id;
        this.table = table;
    }

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
}
