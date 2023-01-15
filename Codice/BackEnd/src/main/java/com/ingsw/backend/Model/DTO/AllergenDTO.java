package com.ingsw.backend.Model.DTO;

public class AllergenDTO {

    //PRIMARY KEY
    private String name;

    // -------------------------------------------------


    //CONSTRUCTORS
    public AllergenDTO() {
    }

    public AllergenDTO(String name) {
        this.name = name;
    }


    // -------------------------------------------------


    //GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
