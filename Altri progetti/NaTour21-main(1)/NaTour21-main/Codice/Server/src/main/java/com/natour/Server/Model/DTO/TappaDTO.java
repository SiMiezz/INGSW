package com.natour.Server.Model.DTO;

public class TappaDTO {

	//Class Primary Key
	private Long id_tappa = -1L;

	//Class Foreign Key
	private Long id_itinerario;

	//Campi Locali
	private String nometappa;
	private Double latitudine;
	private Double longitudine;
	private Integer sequenza;

	/*********************************************************************************************/
	
	//Constructor
	public TappaDTO(Long id_tappa, Long id_itinerario, String nometappa, Double latitudine, Double longitudine, Integer sequenza) {
		super();
		this.id_tappa = id_tappa;
		this.id_itinerario = id_itinerario;
		this.nometappa = nometappa;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.sequenza = sequenza;
	}
	
	public TappaDTO() {}

	/*********************************************************************************************/
	
	//Getter e Setter
	public Long getId_tappa() {
		return id_tappa;
	}

	public void setId_tappa(Long id_tappa) {
		this.id_tappa = id_tappa;
	}

	public Long getId_itinerario() {
		return id_itinerario;
	}

	public void setId_itinerario(Long id_itinerario) {
		this.id_itinerario = id_itinerario;
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
