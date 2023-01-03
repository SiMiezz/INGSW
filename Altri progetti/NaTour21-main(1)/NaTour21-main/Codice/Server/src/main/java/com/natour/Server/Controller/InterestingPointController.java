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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.natour.Server.Exception.RequestApiException;
import com.natour.Server.Model.InterestingPoint;
import com.natour.Server.Model.Itinerario;
import com.natour.Server.Model.DTO.InterestingPointDTO;
import com.natour.Server.Service.InterestingPointService;
import com.natour.Server.Service.ItinerarioService;
import com.natour.Server.Utils.UtilsHeader;

@RestController
@RequestMapping(path = "api/interestingpoint", produces = { "application/json" })
public class InterestingPointController {

	@Autowired
	@Qualifier("mainInterestingPointService")
	private InterestingPointService interestingpointService;

	@Autowired
	@Qualifier("mainItinerarioService")
	private ItinerarioService itinerarioService;
	
	@Autowired
	private ModelMapper modelMapper;

	/*********************************************************************************************/

	//Get Mapping
	@GetMapping(path = "listaInterestingPoint")
	@ResponseBody
	public List<InterestingPoint> getAll() {
		return this.interestingpointService.getAllInterestinPoint();
	}

	@GetMapping(path = "getInterestingPoint/byID/{idInterestingPoint}")
	@ResponseBody
	public InterestingPointDTO getCompilationByID(@PathVariable(name = "idInterestingPoint") Long idInterestingPoint) {
		Optional<InterestingPoint> result = this.interestingpointService.getInterestingPointByID(idInterestingPoint);

		if(result.isEmpty())
			throw new RequestApiException("Interesting Point non trovato.", HttpStatus.NOT_FOUND);

		InterestingPointDTO interestingPoint = convertEntityToDto(result.get());
		return interestingPoint;
	}
	
	@GetMapping(path = "getInterestingPoint/byItinerario/{idItinerario}")
	@ResponseBody
	public List<InterestingPointDTO> getInterestingPointByItinerario(@PathVariable(name = "idItinerario") Long idItinerario) {
		List<InterestingPoint> listaInterestingPoint = this.interestingpointService.getInterestingPointByItinerario(idItinerario);

		if(listaInterestingPoint.isEmpty())
			throw new RequestApiException("L'itinerario non possiede Interesting Point.", HttpStatus.NOT_FOUND);

		List<InterestingPointDTO> ret = new ArrayList<InterestingPointDTO>();
		for(InterestingPoint i : listaInterestingPoint)
			ret.add(convertEntityToDto(i));
		return ret;
	}
	
	@GetMapping(path = "getInterestingPoint/getSingleFoto/{idInterestingPoint}")
	@ResponseBody
	public String getFotoItinerarioSingle(@PathVariable(name = "idInterestingPoint") Long idInterestingPoint) {
		Optional<InterestingPoint> result = this.interestingpointService.getInterestingPointByID(idInterestingPoint);

		if(result.isEmpty())
			throw new RequestApiException("Interesting Point non trovato.", HttpStatus.NOT_FOUND);

		InterestingPointDTO interestingPoint = convertEntityToDto(result.get());
		return interestingPoint.getUrlfoto();
	}

	@GetMapping(path = "getInterestingPoint/getMultipleFoto/{idItinerario}")
	@ResponseBody
	public List<String> getFotoItinerarioMultiple(@PathVariable(name = "idItinerario") Long idItinerario) {
		List<InterestingPointDTO> listaInterestingPoint = this.getInterestingPointByItinerario(idItinerario);
		
		if(listaInterestingPoint.isEmpty())
			throw new RequestApiException("L'itinerario non possiede Interesting Point.", HttpStatus.NOT_FOUND);
		
		List<String> ret = new ArrayList<String>();
		for(InterestingPointDTO i : listaInterestingPoint)
			ret.add(i.getUrlfoto());
		return ret;
	}

	//Post Mapping
	@PostMapping(path = "createInterestingPoint")
	@ResponseBody
	public ResponseEntity<String> createInterestingPoint(@RequestBody InterestingPointDTO interestingPointDTO) {

		InterestingPoint interestingPoint = this.convertDtoToEntity(interestingPointDTO);

		boolean creato = this.interestingpointService.creaInterestingPoint(interestingPoint);
		if(creato) {
			UtilsHeader h = new UtilsHeader("Interesting point salvato.");
			return ResponseEntity.status(HttpStatus.CREATED).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Interesting point non salvato.", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "createInterestingPoints")
	@ResponseBody
	public ResponseEntity<String> createInterestingPoints(@RequestBody List<InterestingPointDTO> interestingPointDTO) {

		List<InterestingPoint> interestingPoint = new ArrayList<InterestingPoint>();
		for(InterestingPointDTO ip : interestingPointDTO)
			interestingPoint.add(convertDtoToEntity(ip));

		boolean creato = this.interestingpointService.creaInterestingPoints(interestingPoint);
		if(creato) {
			UtilsHeader h = new UtilsHeader("InterestingPoint salvati.");
			return ResponseEntity.status(HttpStatus.CREATED).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("InterestingPoint non salvati.", HttpStatus.BAD_REQUEST);
		
	}
	
	//Put Mapping
	@PutMapping(path = "modifyInterestingPoint")
	@ResponseBody
	public ResponseEntity<String> modifyCompilation(@RequestBody InterestingPointDTO interestingPointDTO) {

		InterestingPoint interestingPoint = this.convertDtoToEntity(interestingPointDTO);

		boolean modificato = this.interestingpointService.modificaInterestingPoint(interestingPoint);
		if(modificato) {
			UtilsHeader h = new UtilsHeader("Interesting point modificato.");
			return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Compilation non modificata.", HttpStatus.BAD_REQUEST);
	}

	//Delete Mapping
	@DeleteMapping(path = "deleteInterestingPoint/{idInterestingPoint}")
	@ResponseBody
	public ResponseEntity<String> deleteInterestingPoint(@PathVariable(name = "idInterestingPoint") Long idInterestingPoint) {

		boolean eliminato = this.interestingpointService.cancellaInterestingPoint(idInterestingPoint);
		if(eliminato) {
			UtilsHeader h = new UtilsHeader("Interesting point eliminato.");
			return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("InterestingPoint non eliminato.", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(path = "deleteInterestingPoint/foto/{idInterestingPoint}")
	@ResponseBody
	public ResponseEntity<String> deleteFotoInterestingPoint(@PathVariable(name = "idInterestingPoint") Long idInterestingPoint) {

		boolean eliminato = this.interestingpointService.cancellaFotoInterestingPoint(idInterestingPoint);	
		if(eliminato) {
			UtilsHeader h = new UtilsHeader("Foto dell' interesting point eliminata.");
			return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Foto di InterestingPoint non eliminata.", HttpStatus.BAD_REQUEST);
	}
	
	/*********************************************************************************************/

	//Getter e Setter
	public InterestingPointService getInterestingpointService() {
		return interestingpointService;
	}

	public void setInterestingpointService(InterestingPointService interestingpointService) {
		this.interestingpointService = interestingpointService;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	/*********************************************************************************************/

	//Mapper
	private InterestingPointDTO convertEntityToDto(InterestingPoint interestingPoint) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		InterestingPointDTO interestingPointDTO = new InterestingPointDTO();
		interestingPointDTO = modelMapper.map(interestingPoint, InterestingPointDTO.class);
		
		//Mapping
		Long idItinerario = interestingPoint.getItinerario().getId_itinerario();
		interestingPointDTO.setId_itinerario(idItinerario);
		return interestingPointDTO;
	}

	private InterestingPoint convertDtoToEntity(InterestingPointDTO interestingPointDTO) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		InterestingPoint interestingPoint = new InterestingPoint();
		interestingPoint = modelMapper.map(interestingPointDTO, InterestingPoint.class);

		//Mapping
		Long idItinerario = interestingPointDTO.getId_itinerario();
		Optional<Itinerario> itinerarioOptional = this.itinerarioService.getItinerarioByID(idItinerario);

		Itinerario itinerario = null;
		if(!itinerarioOptional.isEmpty())
			itinerario = itinerarioOptional.get();
		interestingPoint.setItinerario(itinerario);
		return interestingPoint;
	}

	/*********************************************************************************************/

}
