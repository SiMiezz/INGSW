package com.natour.Server.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natour.Server.Model.Compilation;
import com.natour.Server.Repository.CompilationRepository;
import com.natour.Server.Utils.ICompilationService;

@Service("mainCompilationService")
public class CompilationService implements ICompilationService{

	@Autowired
	private CompilationRepository compilationRep;

	/*********************************************************************************************/

	//Methods
	@Override
	public List<Compilation> getAllCompilation(){
		return this.compilationRep.findAll();
	}

	@Override
	public Optional<Compilation> getCompilationByID(Long idCompilation){
		return this.compilationRep.findById(idCompilation);
	}

	@Override
	public List<Compilation> getCompilationByUsername(String username) {
		return this.compilationRep.findByUsername(username);
	}

	@Override
	public List<Long> getItinerariInCompilation(Long idCompilation) {
		return this.compilationRep.getItinerarioInCompilation(idCompilation);
	}
	
	
	@Override
	public List<Compilation> getValidCompilation(String username, long idItinerario) {
		return this.compilationRep.getValidCompilation(username, idItinerario);
	}

	@Override
	public boolean creaCompilation(Compilation compilation) {
		try {
			if(!this.compilationRep.existsById(compilation.getId_compilation()))
				this.compilationRep.save(compilation);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean addItinerarioInCompilation(Long idCompilation, Long idItinerario) {

		Optional<String> str = this.compilationRep.ifExistIC(idCompilation, idItinerario);

		if(str.isEmpty()) {
			this.compilationRep.insertItinerarioInCompilation(idCompilation,idItinerario);
			return true;
		}
		return false;
	}


	@Override
	public boolean modificaCompilation(Compilation compilation) {
		try {
			if(this.compilationRep.existsById(compilation.getId_compilation()))
				this.compilationRep.save(compilation);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean cancellaCompilation(Long id_compilation) {
		try {
			if(this.compilationRep.existsById(id_compilation))
				this.compilationRep.deleteById(id_compilation);
			else
				return false;
		}catch(IllegalArgumentException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean removeItinerarioInCompilation(Long idCompilation, Long idItinerario) {
		
		Optional<String> str = this.compilationRep.ifExistIC(idCompilation, idItinerario);

		if(!str.isEmpty()) {
			this.compilationRep.deleteItinerarioInCompilation(idCompilation, idItinerario);
			return true;
		}
		return false;
	}

	/*********************************************************************************************/

	//Getter e Setter
	public CompilationRepository getCompilationRep() {
		return compilationRep;
	}

	public void setCompilationRep(CompilationRepository compilationRep) {
		this.compilationRep = compilationRep;
	}
	

	/*********************************************************************************************/

}
