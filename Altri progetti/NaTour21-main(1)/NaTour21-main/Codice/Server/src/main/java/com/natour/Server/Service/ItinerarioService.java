package com.natour.Server.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natour.Server.Model.Itinerario;
import com.natour.Server.Repository.ItinerarioRepository;
import com.natour.Server.Utils.IItinerarioService;

@Service("mainItinerarioService")
public class ItinerarioService implements IItinerarioService {

	@Autowired
	private ItinerarioRepository itinerarioRep;

	/*********************************************************************************************/

	//Methods
	@Override
	public List<Itinerario> getAllItinerari() {
		return this.itinerarioRep.findAll();
	}

	@Override
	public List<Itinerario> getAllRecentItinerari() {
		return this.itinerarioRep.findAllByRecent();
	}

	@Override
	public List<Itinerario> getItinerariByName(String nomeItinerario) {
		return this.itinerarioRep.findAllByName(nomeItinerario);

	}
	@Override
	public Optional<Itinerario> getItinerarioByID(Long id_itinerario) {
		return this.itinerarioRep.findById(id_itinerario);
	}

	@Override
	public List<Itinerario> getItinerariByUsername(String username) {
		return this.itinerarioRep.findByUsername(username);
	}

	@Override
	public List<Itinerario> getByFilter(String nome, String puntoinizio, String puntofine,
										Timestamp durata, double lunghezza, String difficulty,
										boolean accessoDisabili, String areageografica) {
		
		return this.itinerarioRep.findByFilter(nome, puntoinizio, puntofine,
												durata, lunghezza, difficulty,
												accessoDisabili, areageografica);
	}

	@Override
	public boolean creaItinerario(Itinerario insertItinerario) {
		try {
			Optional<String> equals = this.itinerarioRep.checkEquals(insertItinerario.getPuntoinizio(),
																	 insertItinerario.getLatitudine_pi(),
																	 insertItinerario.getLongitudine_pi(),
																	 insertItinerario.getPuntofine(),
																	 insertItinerario.getLatitudine_pf(),
																	 insertItinerario.getLongitudine_pf());
			if(!equals.isEmpty())
				return false;
			
			this.itinerarioRep.save(insertItinerario);
			
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	public Optional<Long> getLastId(String username) {
		return this.itinerarioRep.getLastId(username);
	}

	@Override
	public boolean modificaItinerario(Itinerario updateItinerario) {
		try {
			if(this.itinerarioRep.existsById(updateItinerario.getId_itinerario()))
				this.itinerarioRep.save(updateItinerario);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean cancellaItinerario(Long id_itinerario) {
		try {
			if(this.itinerarioRep.existsById(id_itinerario))
				this.itinerarioRep.deleteById(id_itinerario);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	/*********************************************************************************************/

	//Getter e Setter
	public ItinerarioRepository getItinerarioRep() {
		return itinerarioRep;
	}

	public void setItinerarioRep(ItinerarioRepository itinerarioRep) {
		this.itinerarioRep = itinerarioRep;
	}

	/*********************************************************************************************/

}
