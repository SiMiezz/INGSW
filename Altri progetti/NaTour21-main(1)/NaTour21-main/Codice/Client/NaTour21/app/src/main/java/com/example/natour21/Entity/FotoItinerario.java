package com.example.natour21.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FotoItinerario implements Serializable {

	//Class Primary Key
	@Expose
	@SerializedName("id_photo")
	private Long id_photo;

	//Class Foreign Key
	@Expose
	@SerializedName("id_itinerario")
	private Long id_itinerario;

	//Campi Locali
	@Expose
	@SerializedName("urlfoto")
	private String urlfoto;
//	@Expose
//	@SerializedName("latitudine_foto")
//	private String latitudine_foto;
//	@Expose
//	@SerializedName("longitudine_foto")
//	private String longitudine_foto;


	/*********************************************************************************************/
	
	//Constructor
	public FotoItinerario(Long id_photo, Long id_itinerario, String urlfoto) {
		super();
		this.id_photo = id_photo;
		this.id_itinerario = id_itinerario;
		this.urlfoto = urlfoto;
	}

	public FotoItinerario() {}

	@Override
	public String toString() {
		return "FotoItinerario{" +
				"id_photo=" + id_photo +
				", id_itinerario=" + id_itinerario +
				", urlfoto='" + urlfoto + '\'' +
				'}';
	}

	/*********************************************************************************************/
	
	//Getter e Setter
	public Long getId_photo() {
		return id_photo;
	}


	public void setId_photo(Long id_photo) {
		this.id_photo = id_photo;
	}


	public Long getId_itinerario() {
		return id_itinerario;
	}


	public void setId_itinerario(Long id_itinerario) {
		this.id_itinerario = id_itinerario;
	}


	public String getUrlfoto() {
		return urlfoto;
	}


	public void setUrlfoto(String urlfoto) {
		this.urlfoto = urlfoto;
	}

//	public String getLatitudine_foto() {
//		return latitudine_foto;
//	}
//
//	public void setLatitudine_foto(String latitudine_foto) {
//		this.latitudine_foto = latitudine_foto;
//	}
//
//	public String getLongitudine_foto() {
//		return longitudine_foto;
//	}
//
//	public void setLongitudine_foto(String longitudine_foto) {
//		this.longitudine_foto = longitudine_foto;
//	}

	/*********************************************************************************************/
	
}
