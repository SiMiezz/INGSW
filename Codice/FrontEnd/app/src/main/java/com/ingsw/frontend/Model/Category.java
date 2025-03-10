package com.ingsw.frontend.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;

import java.io.Serializable;

public class Category implements Serializable {

    //PRIMARY KEY
    @Expose
    @SerializedName("id")
    private Integer id;

    // ATTRIBUTES
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("aliment")
    private Aliment_Type aliment;

    @Expose
    @SerializedName("posizione")
    private Integer posizione;

    //REFERENCES
    @Expose
    @SerializedName("menuId")
    private Integer menuId;

    //CONSTRUCTORS

    public Category(String name, Aliment_Type aliment, Integer menuId) {
        this.name = name;
        this.aliment = aliment;
        this.menuId = menuId;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    //GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    private Boolean checked = Boolean.valueOf(false);

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    private boolean clicked = false;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
