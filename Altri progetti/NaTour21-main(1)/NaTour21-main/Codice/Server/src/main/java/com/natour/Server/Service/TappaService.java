package com.natour.Server.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natour.Server.Model.Tappa;
import com.natour.Server.Repository.TappaRepository;
import com.natour.Server.Utils.ITappaService;

@Service("mainTappaService")
public class TappaService implements ITappaService{
	
	@Autowired
	private TappaRepository tappaRep;

	/*********************************************************************************************/

	//Methods
	@Override
	public List<Tappa> getAllTappe() {
		return this.tappaRep.findAll();
	}

	@Override
	public Optional<Tappa> getTappaByID(Long idTappa) {
		return this.tappaRep.findById(idTappa);
	}

	@Override
	public List<Tappa> getTappaByItinerario(Long idItinerario) {
		return this.tappaRep.findByItinerario(idItinerario);
	}
	
	@Override
	public boolean creaTappa(Tappa tappa) {
		try {
			if(!this.tappaRep.existsById(tappa.getId_tappa()))
				this.tappaRep.save(tappa);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean creaTappe(List<Tappa> tappe) {
		try {
			if(!tappe.isEmpty())
				this.tappaRep.saveAll(tappe);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean modificaTappa(Tappa tappa) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean cancellaTappa(Tappa tappa) {
		throw new UnsupportedOperationException();
	}

	/*********************************************************************************************/

	//Getter e Setter
	public TappaRepository getTappaRep() {
		return tappaRep;
	}

	public void setTappaRep(TappaRepository tappaRep) {
		this.tappaRep = tappaRep;
	}


	/*********************************************************************************************/

}
