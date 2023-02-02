package com.ingsw.backend.Model.DTO;

import com.ingsw.backend.Model.Enumerations.Aliment_Type;

public class CategoryDTO {

    //PRIMARY KEY
    private Integer id;

    //REFERENCES
    private Integer menuId;

    // ATTRIBUTES
    private String name;

    private Aliment_Type aliment;

    private Integer posizione;

    //CONSTRUCTORS

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, Integer menuId, String name, Aliment_Type aliment, Integer posizione) {
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.aliment = aliment;
        this.posizione = posizione;
    }

    // -------------------------------------------------


    //GETTERS AND SETTERS


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Aliment_Type getAliment() {
        return aliment;
    }

    public void setAliment(Aliment_Type aliment) {
        this.aliment = aliment;
    }

    public Integer getPosizione() {
        return posizione;
    }

    public void setPosizione(Integer posizione) {
        this.posizione = posizione;
    }
}
