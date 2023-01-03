package com.natour.Server.Utils;

import java.util.List;
import java.util.Optional;

import com.natour.Server.Model.FotoItinerario;

public interface IFotoItinerario {

	/*********************************************************************************************/

	//Methods GET
	List<FotoItinerario> getAllFotoItinerario();
	Optional<FotoItinerario> getFotoByID(Long idFoto);
	List<FotoItinerario> getFotoItinerarioByItinerario(Long idItinerario);
	long getCountFoto(Long idItinerario);
	
	//Methods POST
	boolean inserisciFotoItinerario(FotoItinerario fotoItinerario);

	//Methods PUT
	boolean modificaFotoItinerario(FotoItinerario fotoItinerario);
	
	//Methods DELETE
	boolean eliminaFotoItinerario(Long idItinerario);
	
}
