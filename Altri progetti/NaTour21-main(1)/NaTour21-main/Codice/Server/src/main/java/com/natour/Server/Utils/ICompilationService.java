package com.natour.Server.Utils;

import java.util.List;
import java.util.Optional;

import com.natour.Server.Model.Compilation;

public interface ICompilationService {

	//Methods GET
	List<Compilation> getAllCompilation();
	Optional<Compilation> getCompilationByID(Long idcompilation);
	List<Compilation> getCompilationByUsername(String username);
	List<Long> getItinerariInCompilation(Long idCompilation);
	List<Compilation> getValidCompilation(String username, long idItinerario);
	
	//Methods POST
	boolean creaCompilation(Compilation utente);
	boolean addItinerarioInCompilation(Long idCompilation, Long idItinerario);
	
	//Methods PUT
	boolean modificaCompilation(Compilation compilation);
	
	//Methods DELETE
	boolean cancellaCompilation(Long id_compilation);
	boolean removeItinerarioInCompilation(Long idCompilation, Long idItinerario);

}
