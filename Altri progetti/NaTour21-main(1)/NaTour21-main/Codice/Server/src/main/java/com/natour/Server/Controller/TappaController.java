package com.natour.Server.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.natour.Server.Exception.RequestApiException;
import com.natour.Server.Model.Itinerario;
import com.natour.Server.Model.Tappa;
import com.natour.Server.Model.DTO.TappaDTO;
import com.natour.Server.Service.ItinerarioService;
import com.natour.Server.Service.TappaService;
import com.natour.Server.Utils.UtilsHeader;

@RestController
@RequestMapping(path = "api/tappa", produces = { "application/json" })
public class TappaController {

	@Autowired
	@Qualifier("mainTappaService")
	private TappaService tappaService;

	@Autowired
	@Qualifier("mainItinerarioService")
	private ItinerarioService itinerarioService;

	@Autowired
	private ModelMapper modelMapper;

	/*********************************************************************************************/

	//Get Mapping
	@GetMapping(path = "listaTappe")
	@ResponseBody
	public List<Tappa> getAll() {
		return this.tappaService.getAllTappe();
	}

	@GetMapping(path = "getTappa/ByID/{idTappa}")
	public TappaDTO getTappaByID(@PathVariable(name = "idTappa") Long idTappa) {
		Optional<Tappa> result = this.tappaService.getTappaByID(idTappa);

		if(result.isEmpty())
			throw new RequestApiException("Tappa non trovata.", HttpStatus.NOT_FOUND);

		TappaDTO tappa = convertEntityToDto(result.get());
		return tappa;
	}

	@GetMapping(path = "getComplation/byItinerario/{idItinerario}")
	@ResponseBody
	public List<TappaDTO> getTappaByItinerario(@PathVariable(name = "idItinerario") Long idItinerario) {
		List<Tappa> listaTappe = this.tappaService.getTappaByItinerario(idItinerario);

		if(listaTappe.isEmpty())
			throw new RequestApiException("L'itinerario non possiede tappe.", HttpStatus.NOT_FOUND);

		List<TappaDTO> ret = new ArrayList<TappaDTO>();
		for(Tappa t : listaTappe)
			ret.add(convertEntityToDto(t));

		return ret;
	}

	//Post Mapping
	@PostMapping(path = "createTappa")
	@ResponseBody
	public ResponseEntity<String> createTappa(@RequestBody TappaDTO tappaDTO) {

		Tappa tappa = this.convertDtoToEntity(tappaDTO);

		boolean creato = this.tappaService.creaTappa(tappa);
		if(creato) {
			UtilsHeader h = new UtilsHeader("Tappa salvata.");
			return ResponseEntity.status(HttpStatus.CREATED).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Tappa non salvata.", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "createTappe")
	@ResponseBody
	public ResponseEntity<String> createTappe(@RequestBody List<TappaDTO> tappeDTO) {

		List<Tappa> tappe = new ArrayList<Tappa>();
		for(TappaDTO t : tappeDTO)
			tappe.add(convertDtoToEntity(t));

		boolean creato = this.tappaService.creaTappe(tappe);
		if(creato) {
			UtilsHeader h = new UtilsHeader("Tappe salvate.");
			return ResponseEntity.status(HttpStatus.CREATED).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Tappe non salvate.", HttpStatus.BAD_REQUEST);
	}

	//Put Mapping
	//Not implemented.

	//Delete Mapping
	//Nont implemented.

	/*********************************************************************************************/

	//Getter e Setter
	public TappaService getTappaService() {
		return tappaService;
	}

	public void setTappaService(TappaService tappaService) {
		this.tappaService = tappaService;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	/*********************************************************************************************/

	//Mapper
	private TappaDTO convertEntityToDto(Tappa tappa) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		TappaDTO tappaDTO = new TappaDTO();
		tappaDTO = modelMapper.map(tappa, TappaDTO.class);

		//Mapping
		Long id_itinerario = tappa.getItinerario().getId_itinerario();
		tappaDTO.setId_itinerario(id_itinerario);
		return tappaDTO;
	}

	private Tappa convertDtoToEntity(TappaDTO tappaDTO) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		Tappa tappa = new Tappa();
		tappa = modelMapper.map(tappaDTO, Tappa.class);

		//Mapping
		Long id_itinerario = tappaDTO.getId_itinerario();
		Optional<Itinerario> itinerarioOptional = this.itinerarioService.getItinerarioByID(id_itinerario);

		Itinerario itinerario = null;
		if(!itinerarioOptional.isEmpty())
			itinerario = itinerarioOptional.get();
		tappa.setItinerario(itinerario);
		return tappa;
	}

	/*********************************************************************************************/

}
