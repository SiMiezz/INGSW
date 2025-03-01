package com.ingsw.backend.Model;

import com.fasterxml.jackson.annotation.*;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Category implements Serializable {

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private Integer id;


    //REFERENCES
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Element> elementList = new ArrayList<>();

    @ManyToOne(fetch =  FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="menu_id", referencedColumnName = "idMenu")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference
    private Menu menu;

    // ATTRIBUTES
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "aliment", columnDefinition = "ENUM('food', 'drink')", nullable = false)
    @Enumerated(EnumType.STRING)
    private Aliment_Type aliment;

    @Column(name = "posizione", nullable = false, insertable = false)
    private Integer posizione;

    //CONSTRUCTORS
    public Category() {
    }

    public Category(Integer id, Menu menu, String name, Aliment_Type aliment, Integer posizione) {
        this.id = id;
        this.menu = menu;
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

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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

    // -------------------------------------------------


}
