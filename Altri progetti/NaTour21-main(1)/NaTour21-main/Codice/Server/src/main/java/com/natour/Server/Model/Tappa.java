package com.natour.Server.Model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tappa")
public class Tappa implements Serializable {

	//Class Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tappa")
	private Long id_tappa;

	//Class Foreign Key
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_itinerario",
				nullable = false,
				referencedColumnName = "id_itinerario")
	private Itinerario itinerario;

	//Campi Locali
	@Column(name="nometappa")
	private String nometappa;
	@Column(name="latitudine")
	private Double latitudine;
	@Column(name="longitudine")
	private Double longitudine;
	@Column(name="sequenza")
	private Integer sequenza;

	/*********************************************************************************************/

	//Constructor
	public Tappa(Long id_tappa, Itinerario itinerario, String nometappa, Double latitudine, Double longitudine, Integer sequenza) {
		super();
		this.id_tappa = id_tappa;
		this.itinerario = itinerario;
		this.nometappa = nometappa;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.sequenza = sequenza;
	}

	public Tappa() {}

	/*********************************************************************************************/

	//Getter e Setter
	public Long getId_tappa() {
		return id_tappa;
	}

	public void setId_tappa(Long id_tappa) {
		this.id_tappa = id_tappa;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public String getNometappa() {
		return nometappa;
	}

	public void setNometappa(String nometappa) {
		this.nometappa = nometappa;
	}

	public Double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(Double latitudine) {
		this.latitudine = latitudine;
	}

	public Double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(Double longitudine) {
		this.longitudine = longitudine;
	}

	public Integer getSequenza() {
		return sequenza;
	}

	public void setSequenza(Integer sequenza) {
		this.sequenza = sequenza;
	}
	
	/*********************************************************************************************/
}
