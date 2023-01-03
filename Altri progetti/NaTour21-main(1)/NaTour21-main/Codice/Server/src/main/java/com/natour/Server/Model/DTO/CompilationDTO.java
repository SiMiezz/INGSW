package com.natour.Server.Model.DTO;

public class CompilationDTO {

	//Class Primary Key
	private Long id_compilation = -1L;

	//Class Foreign Key
	private String id_utente;

	//Campi Locali
	private String titolo;
	private String descrizione;
	
	/*********************************************************************************************/
	
	//Constructor
	public CompilationDTO(String id_utente, String titolo, String descrizione) {
		super();
		//this.id_compilation = id_compilation;
		this.id_utente = id_utente;
		this.titolo = titolo;
		this.descrizione = descrizione;
	}
	
	public CompilationDTO() {}
	
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
