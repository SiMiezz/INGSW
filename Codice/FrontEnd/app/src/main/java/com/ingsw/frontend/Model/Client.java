package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    //PRIMARY KEY
    @Expose
    private Integer id;

    //REFERENCES
    @Expose
    @SerializedName("table_id")
    private Integer tableId;

    //CONSTRUCTORS
    public Client() {

    }

    public Client(Integer id, Integer tableId) {
        this.id = id;
        this.tableId = tableId;
    }

    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
}
