package com.natour.Server.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="compilation")
public class Compilation implements Serializable {

	//Class Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_compilation")
	private Long id_compilation;

	//Class Foreign Key
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_utente",
    			nullable = false,
    			referencedColumnName = "username")
	private User utente;
	
	//Relationship
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "compilation_itinerario_map",
				uniqueConstraints = {@UniqueConstraint(columnNames = {"id_compilation_map", "id_itinerario_map"})},
	          	joinColumns = @JoinColumn(name = "id_compilation_map", referencedColumnName = "id_compilation"),
	          	inverseJoinColumns = @JoinColumn(name = "id_itinerario_map", referencedColumnName = "id_itinerario")
	)
	private List<Itinerario> itinerari = new ArrayList<Itinerario>();
	
	//Campi Locali
	@Column(name="titolo", nullable = false)
	private String titolo;
	@Column(name="descrizione")
	private String descrizione;

	/*********************************************************************************************/

	//Constructor
	public Compilation(Long id_compilation, User utente, String titolo, String descrizione) {
		super();
		this.id_compilation = id_compilation;
		this.utente = utente;
		this.titolo = titolo;
		this.descrizione = descrizione;
	}

	public Compilation() {}

	/*********************************************************************************************/

	//Getter e Setter
	public Long getId_compilation() {
		return id_compilation;
	}

	public void setId_compilation(Long id_compilation) {
		this.id_compilation = id_compilation;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public List<Itinerario> getItinerari() {
		return itinerari;
	}

	public void setItinerari(List<Itinerario> itinerari) {
		this.itinerari = itinerari;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/*********************************************************************************************/

}
