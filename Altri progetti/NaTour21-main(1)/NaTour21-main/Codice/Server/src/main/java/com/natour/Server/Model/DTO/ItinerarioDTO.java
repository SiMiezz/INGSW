package com.natour.Server.Model.DTO;

public class ItinerarioDTO {

	//Class Primary Key
	private Long id_itinerario = -1L;

	//Class Foreign Key
	private String id_utente;

	//Campi Locali
	private String titolo;
	private String descrizione;
	private String puntoinizio;
	private Double latitudine_pi;
	private Double longitudine_pi;
	private String puntofine;
	private Double latitudine_pf;
	private Double longitudine_pf;
	private Boolean accessodisabili;
	private String difficulty;
	private String durata;
	private Double lunghezza;
	private String areageografica;

	/*********************************************************************************************/

	//Constructor
	public ItinerarioDTO(Long id_itinerario, String id_utente, String titolo, String descrizione, String puntoinizio,
			Double latitudine_pi, Double longitudine_pi, String puntofine, Double latitudine_pf, Double longitudine_pf,
			Boolean accessodisabili, String difficulty, String durata, Double lunghezza, String areageografica) {
		super();
		this.id_itinerario = id_itinerario;
		this.id_utente = id_utente;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.puntoinizio = puntoinizio;
		this.latitudine_pi = latitudine_pi;
		this.longitudine_pi = longitudine_pi;
		this.puntofine = puntofine;
		this.latitudine_pf = latitudine_pf;
		this.longitudine_pf = longitudine_pf;
		this.accessodisabili = accessodisabili;
		this.difficulty = difficulty;
		this.durata = durata;
		this.lunghezza = lunghezza;
		this.areageografica = areageografica;
	}
	
	public ItinerarioDTO() {}
	
	/*********************************************************************************************/

	//Getter e Setter
	public Long getId_itinerario() {
		return id_itinerario;
	}

	public void setId_itinerario(Long id_itinerario) {
		this.id_itinerario = id_itinerario;
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

	public String getPuntoinizio() {
		return puntoinizio;
	}

	public void setPuntoinizio(String puntoinizio) {
		this.puntoinizio = puntoinizio;
	}

	public Double getLatitudine_pi() {
		return latitudine_pi;
	}

	public void setLatitudine_pi(Double latitudine_pi) {
		this.latitudine_pi = latitudine_pi;
	}

	public Double getLongitudine_pi() {
		return longitudine_pi;
	}

	public void setLongitudine_pi(Double longitudine_pi) {
		this.longitudine_pi = longitudine_pi;
	}

	public String getPuntofine() {
		return puntofine;
	}

	public void setPuntofine(String puntofine) {
		this.puntofine = puntofine;
	}

	public Double getLatitudine_pf() {
		return latitudine_pf;
	}

	public void setLatitudine_pf(Double latitudine_pf) {
		this.latitudine_pf = latitudine_pf;
	}

	public Double getLongitudine_pf() {
		return longitudine_pf;
	}

	public void setLongitudine_pf(Double longitudine_pf) {
		this.longitudine_pf = longitudine_pf;
	}

	public Boolean getAccessodisabili() {
		return accessodisabili;
	}

	public void setAccessodisabili(Boolean accessodisabili) {
		this.accessodisabili = accessodisabili;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public Double getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(Double lunghezza) {
		this.lunghezza = lunghezza;
	}

	public String getAreageografica() {
		return areageografica;
	}

	public void setAreageografica(String areageografica) {
		this.areageografica = areageografica;
	}

	@Override
	public String toString() {
		return "ItinerarioDTO [id_itinerario=" + id_itinerario + ", id_utente=" + id_utente + ", titolo=" + titolo
				+ ", descrizione=" + descrizione + ", puntoinizio=" + puntoinizio + ", latitudine_pi=" + latitudine_pi
				+ ", longitudine_pi=" + longitudine_pi + ", puntofine=" + puntofine + ", latitudine_pf=" + latitudine_pf
				+ ", longitudine_pf=" + longitudine_pf + ", accessodisabili=" + accessodisabili + ", difficulty="
				+ difficulty + ", durata=" + durata + ", lunghezza=" + lunghezza + ", areageografica=" + areageografica
				+ "]";
	}
	
	/*********************************************************************************************/

}
