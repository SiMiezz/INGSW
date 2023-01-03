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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.natour.Server.Exception.RequestApiException;
import com.natour.Server.Model.FotoItinerario;
import com.natour.Server.Model.Itinerario;
import com.natour.Server.Model.DTO.FotoItinerarioDTO;
import com.natour.Server.Service.FotoItinerarioService;
import com.natour.Server.Service.ItinerarioService;
import com.natour.Server.Utils.UtilsHeader;

@RestController
@RequestMapping(path = "api/fotoItinerario", produces = { "application/json" })
public class FotoItinerarioController {

	@Autowired
	@Qualifier("mainFotoItinerarioService")
	private FotoItinerarioService fotoItinerarioService;
	
	@Autowired
	@Qualifier("mainItinerarioService")
	private ItinerarioService itinerarioService;

	@Autowired
	private ModelMapper modelMapper;

	/*********************************************************************************************/

	//Get Mapping
	@GetMapping(path = "listaFotoItinerario")
	@ResponseBody
	public List<FotoItinerarioDTO> getAll() {
		List<FotoItinerario> listaItinerari = this.fotoItinerarioService.getAllFotoItinerario();

		if(listaItinerari.isEmpty())
			throw new RequestApiException("Nessuna foto dell'itinerario trovata.", HttpStatus.NOT_FOUND);

		List<FotoItinerarioDTO> ret = new ArrayList<FotoItinerarioDTO>();

		for(FotoItinerario fi : listaItinerari)
			ret.add(convertEntityToDto(fi));

		return ret;
	}

	@GetMapping(path = "getFoto/{idFoto}")
	@ResponseBody
	public FotoItinerarioDTO getFotoItinerarioByID(@PathVariable(name = "idFoto") Long idFoto) {
		Optional<FotoItinerario> fotoItinerario = this.fotoItinerarioService.getFotoByID(idFoto);

		if(fotoItinerario.isEmpty())
			throw new RequestApiException("Nessuna foto trovata.", HttpStatus.NOT_FOUND);
		
		FotoItinerarioDTO foto = convertEntityToDto(fotoItinerario.get());

		return foto;
	}
	
	@GetMapping(path = "listaFotoItinerario/{idItinerario}")
	@ResponseBody
	public List<FotoItinerarioDTO> getFotoItinerarioByItinerario(@PathVariable(name = "idItinerario") Long idItinerario) {
		List<FotoItinerario> listaFotoItinerario = this.fotoItinerarioService.getFotoItinerarioByItinerario(idItinerario);

		if(listaFotoItinerario.isEmpty())
			throw new RequestApiException("Nessuna foto dell'itinerario trovata.", HttpStatus.NOT_FOUND);

		List<FotoItinerarioDTO> ret = new ArrayList<FotoItinerarioDTO>();

		for(FotoItinerario fi : listaFotoItinerario)
			ret.add(convertEntityToDto(fi));

		return ret;
	}

	@GetMapping(path = "listaFotoItinerario/count/{idItinerario}")
	@ResponseBody
	public long getCountFotoItinerario(@PathVariable(name = "idItinerario") Long idItinerario) {
		return this.fotoItinerarioService.getCountFoto(idItinerario);
	}

	//Post Mapping
	@PostMapping(path = "pubblicaFoto")
	@ResponseBody
	public ResponseEntity<String> publshFoto(@RequestBody FotoItinerarioDTO fotoDTO) {

		FotoItinerario fotoItinerario = this.convertDtoToEntity(fotoDTO);

		boolean creato = this.fotoItinerarioService.inserisciFotoItinerario(fotoItinerario);
		if(creato) {
			UtilsHeader h = new UtilsHeader("Foto dell'itinerario salvata.");
			return ResponseEntity.status(HttpStatus.CREATED).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Foto dell'itinerario non salvata.", HttpStatus.BAD_REQUEST);
		
	}

	//Put Mapping
	//Not implemented.

	//Delete Mapping
	@DeleteMapping(path = "eliminaFoto/{idFoto}")
	@ResponseBody
	public ResponseEntity<String> deleteFoto(@PathVariable(name = "idFoto") Long idFoto) {
		
		boolean eliminato = this.fotoItinerarioService.eliminaFotoItinerario(idFoto);
		if(eliminato) {
			UtilsHeader h = new UtilsHeader("Foto dell'itinerario salvata.");
			return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Itinerario non eliminato.", HttpStatus.BAD_REQUEST);
	}

	/*********************************************************************************************/

	//Getter e Setter
	public FotoItinerarioService getFotoInterestingPointService() {
		return fotoItinerarioService;
	}

	public void setFotoInterestingPointService(FotoItinerarioService fotoItinerarioService) {
		this.fotoItinerarioService = fotoItinerarioService;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	/*********************************************************************************************/

	//Mapper
	private FotoItinerarioDTO convertEntityToDto(FotoItinerario fotoIP) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		FotoItinerarioDTO fotoIPDTO = new FotoItinerarioDTO();
		fotoIPDTO = modelMapper.map(fotoIP, FotoItinerarioDTO.class);
		
		//Mapping
		Long id_itinerario = fotoIP.getItinerario().getId_itinerario();
		fotoIPDTO.setId_itinerario(id_itinerario);
		return fotoIPDTO;
	}

	private FotoItinerario convertDtoToEntity(FotoItinerarioDTO fotoIPDTO) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		FotoItinerario fotoIP = new FotoItinerario();
		fotoIP = modelMapper.map(fotoIPDTO, FotoItinerario.class);
		
		//Mapping
		Long id_itinerario = fotoIPDTO.getId_itinerario();
		Optional<Itinerario> itinerarioOptional = this.itinerarioService.getItinerarioByID(id_itinerario);

		Itinerario itinerario = null;
		if(!itinerarioOptional.isEmpty())
			itinerario = itinerarioOptional.get();
		fotoIP.setItinerario(itinerario);
		return fotoIP;
	}

	/*********************************************************************************************/

}
