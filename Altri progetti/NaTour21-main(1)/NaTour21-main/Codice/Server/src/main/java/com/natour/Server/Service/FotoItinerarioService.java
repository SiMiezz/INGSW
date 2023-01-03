package com.natour.Server.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natour.Server.Model.FotoItinerario;
import com.natour.Server.Repository.FotoItinerarioRepository;
import com.natour.Server.Utils.IFotoItinerario;

@Service("mainFotoItinerarioService")
public class FotoItinerarioService implements IFotoItinerario {

	@Autowired
	private FotoItinerarioRepository fotoItinerarioRep;

	/*********************************************************************************************/

	//Methods
	@Override
	public List<FotoItinerario> getAllFotoItinerario() {
		return this.fotoItinerarioRep.findAll();
	}
	
	@Override
	public Optional<FotoItinerario> getFotoByID(Long idFoto) {
		return this.fotoItinerarioRep.findById(idFoto);
	}
	
	@Override
	public List<FotoItinerario> getFotoItinerarioByItinerario(Long idItinerario) {
		return this.fotoItinerarioRep.findByItinerario(idItinerario);
	}

	@Override
	public long getCountFoto(Long idItinerario) {
		return this.fotoItinerarioRep.getCount(idItinerario);
	}
	
	@Override
	public boolean inserisciFotoItinerario(FotoItinerario fotoItinerario) {
		try {
			if(!this.fotoItinerarioRep.existsById(fotoItinerario.getId_photo()))
				this.fotoItinerarioRep.save(fotoItinerario);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean modificaFotoItinerario(FotoItinerario fotoItinerario) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean eliminaFotoItinerario(Long idFoto) {
		try {
			if(this.fotoItinerarioRep.existsById(idFoto))
				this.fotoItinerarioRep.deleteById(idFoto);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	/*********************************************************************************************/

	//Getter e Setter
	public FotoItinerarioRepository getFotoItinerario() {
		return fotoItinerarioRep;
	}

	public void setFotoItinerario(FotoItinerarioRepository fotoItinerarioRep) {
		this.fotoItinerarioRep = fotoItinerarioRep;
	}
	
	/*********************************************************************************************/

}
