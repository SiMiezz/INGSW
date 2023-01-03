package com.natour.Server.Utils;

import java.util.List;
import java.util.Optional;

import com.natour.Server.Model.Tappa;

public interface ITappaService {

	//Methods GET
	List<Tappa> getAllTappe();
	Optional<Tappa> getTappaByID(Long idTappa);
	List<Tappa> getTappaByItinerario(Long idItinerario);
	
	//Methods POST
	boolean creaTappa(Tappa tappa);
	boolean creaTappe(List<Tappa> tappe);
	
	//Methods PUT
	boolean modificaTappa(Tappa tappa);
	
	//Methods DELETE
	boolean cancellaTappa(Tappa tappa);
}
