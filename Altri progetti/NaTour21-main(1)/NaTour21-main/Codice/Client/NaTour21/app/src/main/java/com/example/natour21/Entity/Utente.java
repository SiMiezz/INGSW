package com.example.natour21.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Utente implements Serializable {

	//Class Primary Key
	@Expose
	@SerializedName("username")
	private String username;
	
	//Campi Locali
	@Expose
	@SerializedName("email")
	private String email;
	@Expose
	@SerializedName("nome")
	private String nome;
	@Expose
	@SerializedName("cognome")
	private String cognome;
	@Expose
	@SerializedName("photolnk")
	private String photolnk;
	
	/*********************************************************************************************/
	
	//Constructor
	public Utente(String username, String email, String nome, String cognome, String photolnk) {
		super();
		this.username = username;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.photolnk = photolnk;
	}
	
	public Utente() {}

	@Override
	public String toString() {
		return "Utente{" +
				"username='" + username + '\'' +
				", email='" + email + '\'' +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				", photolnk='" + photolnk + '\'' +
				'}';
	}

	/*********************************************************************************************/
	
	//Getter e Setter
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPhotolnk() {
		return photolnk;
	}

	public void setPhotolnk(String photolnk) {
		this.photolnk = photolnk;
	}

	/*********************************************************************************************/
	
}
