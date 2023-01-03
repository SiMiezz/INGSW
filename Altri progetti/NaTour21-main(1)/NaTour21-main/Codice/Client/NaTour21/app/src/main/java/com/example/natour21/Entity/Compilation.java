package com.example.natour21.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Compilation implements Serializable {

	//Class Primary Key
	@Expose
	@SerializedName("id_compilation")
	private Long id_compilation;

	//Class Foreign Key
	@Expose
	@SerializedName("id_utente")
	private String id_utente;

	//Campi Locali
	@Expose
	@SerializedName("titolo")
	private String titolo;
	@Expose
	@SerializedName("descrizione")
	private String descrizione;
	
	/*********************************************************************************************/
	
	//Constructor
	public Compilation(String id_utente, String titolo, String descrizione) {
		super();
		//this.id_compilation = id_compilation;
		this.id_utente = id_utente;
		this.titolo = titolo;
		this.descrizione = descrizione;
	}
	
	public Compilation() {}

	@Override
	public String toString() {
		return "Compilation{" +
				"id_compilation=" + id_compilation +
				", id_utente='" + id_utente + '\'' +
				", titolo='" + titolo + '\'' +
				", descrizione='" + descrizione + '\'' +
				'}';
	}

	/*********************************************************************************************/
	
	//Getter e Setter
	public Long getId_compilation() {
		return id_compilation;
	}

	public void setId_compilation(Long id_compilation) {
		this.id_compilation = id_compilation;
	}

	public String getId_utente() {
		return id_utente;
	}

	public void setId_utente(String id_utente) {
		this.id_utente = id_utente;
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
