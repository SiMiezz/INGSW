package com.natour.Server.Utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.natour.Server.Model.Itinerario;

public interface IItinerarioService {
	
	//Methods GET
	List<Itinerario> getAllItinerari();
	List<Itinerario> getAllRecentItinerari();
	
	List<Itinerario> getItinerariByUsername(String username);
	List<Itinerario> getItinerariByName(String nomeItinerario);
	Optional<Itinerario> getItinerarioByID(Long id_itinerario);
	
	List<Itinerario> getByFilter(String nome, String puntoinizio, String puntofine,
									Timestamp durata, double lunghezza, String difficulty,
									boolean accessoDisabili, String areageografica);
	//Methods POST
	boolean creaItinerario(Itinerario insertItinerario);
	
	//Methods PUT
	boolean modificaItinerario(Itinerario updateItinerario);
	
	//Methods DELETE
	boolean cancellaItinerario(Long id_itinerario);
	
	
}
