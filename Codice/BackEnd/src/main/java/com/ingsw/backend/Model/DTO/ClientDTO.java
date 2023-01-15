package com.ingsw.backend.Model.DTO;

public class ClientDTO {

    private Integer id;

    //REFERENCES
    private Integer tableId;

    // -------------------------------------------------


    //CONSTRUCTORS
    public ClientDTO() {

    }

    public ClientDTO(Integer id, Integer tableId) {
        this.id = id;
        this.tableId = tableId;
    }

    // -------------------------------------------------


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
