package com.natour.Server.Controller;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.natour.Server.Exception.RequestApiException;
import com.natour.Server.Model.Itinerario;
import com.natour.Server.Model.User;
import com.natour.Server.Model.DTO.ItinerarioDTO;
import com.natour.Server.Service.ItinerarioService;
import com.natour.Server.Service.UserService;
import com.natour.Server.Utils.UtilsHeader;

@RestController
@RequestMapping(path = "api/itinerario", produces = { "application/json" })
public class ItinerarioController {

	@Autowired
	@Qualifier("mainItinerarioService")
	private ItinerarioService itinerarioService;

	@Autowired
	@Qualifier("mainUserService")
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	/*********************************************************************************************/

	//Get Mapping
	@GetMapping(path = "listaItinerari")
	@ResponseBody
	public List<ItinerarioDTO> getAll() {
		List<Itinerario> listaItinerari = this.itinerarioService.getAllItinerari();
		
		if(listaItinerari.isEmpty())
			throw new RequestApiException("Nessun itinerario trovato.", HttpStatus.NOT_FOUND);
		
		List<ItinerarioDTO> ret = new ArrayList<ItinerarioDTO>();

		for(Itinerario i : listaItinerari)
			ret.add(convertEntityToDto(i));

		return ret;
	}

	@GetMapping(path = "listaItinerari/orderByRecent")
	public List<ItinerarioDTO> getAllRecent() {
		List<Itinerario> listaItinerari = this.itinerarioService.getAllRecentItinerari();
		
		if(listaItinerari.isEmpty())
			throw new RequestApiException("Nessun itinerario trovato.", HttpStatus.NOT_FOUND);
		
		List<ItinerarioDTO> ret = new ArrayList<ItinerarioDTO>();

		for(Itinerario i : listaItinerari)
			ret.add(convertEntityToDto(i));

		return ret;
	}
	
	@GetMapping(path = "listaItinerari/ByName/{nomeItinerario}")
	public List<ItinerarioDTO> getItinerariByName(@PathVariable(name = "nomeItinerario") String nomeItinerario) {
		List<Itinerario> listaItinerari = this.itinerarioService.getItinerariByName(nomeItinerario);
		
		if(listaItinerari.isEmpty())
			throw new RequestApiException("Nessun itinerario con questo nome trovato.", HttpStatus.NOT_FOUND);
		
		List<ItinerarioDTO> ret = new ArrayList<ItinerarioDTO>();

		for(Itinerario i : listaItinerari)
			ret.add(convertEntityToDto(i));

		return ret;
	}
	
	@GetMapping(path = "getItinerario/byID/{idItinerario}")
	@ResponseBody
	public ItinerarioDTO getItinerarioByID(@PathVariable(name = "idItinerario") Long idItinerario) {
		Optional<Itinerario> result = this.itinerarioService.getItinerarioByID(idItinerario);
		
		if(result.isEmpty())
			throw new RequestApiException("Itinerario non trovato.", HttpStatus.NOT_FOUND);
		
		ItinerarioDTO itinerario = convertEntityToDto(result.get());
		return itinerario;
	}

	@GetMapping(path = "getItinerario/byUsername/{username}")
	@ResponseBody
	public List<ItinerarioDTO> getItinerarioByUsername(@PathVariable(name = "username") String username) {
		List<Itinerario> listaItinerari = this.itinerarioService.getItinerariByUsername(username);

		if(listaItinerari.isEmpty())
			throw new RequestApiException("L'utente non possiede itinerari.", HttpStatus.NOT_FOUND);

		List<ItinerarioDTO> ret = new ArrayList<ItinerarioDTO>();

		for(Itinerario i : listaItinerari)
			ret.add(convertEntityToDto(i));
		
		return ret;
	}
	
	@GetMapping(path = "getItinerario/byFilter")
	@ResponseBody
	public List<ItinerarioDTO> getItinerarioByFilter(@RequestParam Map<String,String> filters) {
		
		Map<String,String> realFilter = checkFilter(filters);
		
		Timestamp durata = null;
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		
		try {
			Date d = new Date(formatter.parse(realFilter.get("durata")).getTime());
			durata = new Timestamp(d.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Itinerario> listaItinerari = this.itinerarioService.getByFilter(
				realFilter.get("titolo"),
				realFilter.get("puntoinizio"),
				realFilter.get("puntofine"),
				durata,
				Double.parseDouble(realFilter.get("length")),
				realFilter.get("difficulty"),
				Boolean.parseBoolean(realFilter.get("accessodisabili")),
				realFilter.get("areageografica"));
		
		if(listaItinerari.isEmpty())
			throw new RequestApiException("Nessun itinerario trovato.", HttpStatus.NOT_FOUND);

		List<ItinerarioDTO> ret = new ArrayList<ItinerarioDTO>();

		for(Itinerario i : listaItinerari)
			ret.add(convertEntityToDto(i));
		
		return ret;
		
	}
	
	//Post Mapping
	@PostMapping(path = "createItinerario")
	@ResponseBody
	public ResponseEntity<String> createItinerario(@RequestBody ItinerarioDTO itinerarioDTO) {
		
		Itinerario itinerario = this.convertDtoToEntity(itinerarioDTO);

		boolean creato = this.itinerarioService.creaItinerario(itinerario);
		if(creato) {
			UtilsHeader h = new UtilsHeader("Itinerario salvato.");
			Optional<Long> id = this.itinerarioService.getLastId(itinerario.getUtente().getUsername());
			h.addHeader("id_itinerario", id.get().toString());
			return ResponseEntity.status(HttpStatus.CREATED).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Itinerario non salvato.", HttpStatus.BAD_REQUEST);
	}
	
	//Put Mapping
	@PutMapping(path = "modifyItinerario")
	@ResponseBody
	public ResponseEntity<String> modifyItinerario(@RequestBody ItinerarioDTO itinerarioDTO) {

		Itinerario itinerario = this.convertDtoToEntity(itinerarioDTO);

		boolean modificato = this.itinerarioService.modificaItinerario(itinerario);
		if(modificato) {
			UtilsHeader h = new UtilsHeader("Itinerario modificato.");
			return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Itinerario non modificato.", HttpStatus.BAD_REQUEST);
	}

	//Delete Mapping
	@DeleteMapping(path = "deleteItinerario/{id_itinerario}")
	@ResponseBody
	public ResponseEntity<String> removeItinerario(@PathVariable(name = "id_itinerario") Long id_itinerario) {

		boolean eliminato = this.itinerarioService.cancellaItinerario(id_itinerario);
		if(eliminato) {
			UtilsHeader h = new UtilsHeader("Itinerario eliminato.");
			return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Itinerario non eliminato.", HttpStatus.BAD_REQUEST);
	}

	/*********************************************************************************************/

	//Getter e Setter
	public ItinerarioService getItinerarioService() {
		return itinerarioService;
	}

	public void setItinerarioService(ItinerarioService itinerarioService) {
		this.itinerarioService = itinerarioService;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	/*********************************************************************************************/

	//Check Filtri
	private Map<String,String> checkFilter(Map<String,String> map) {
		HashMap<String,String> validator = createDefault();
		for(String key : validator.keySet()) {
			if(map.containsKey(key))
				if(map.get(key)!=null)
					validator.put(key, map.get(key));
		}
		return validator;
	}
	
	private HashMap<String, String> createDefault() {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("titolo","");
		map.put("puntoinizio","");
		map.put("puntofine","");
		map.put("durata","23:59:00");
		map.put("length","100");
		map.put("difficulty","");
		map.put("accessodisabili","false");
        map.put("areageografica","");
        map.put("url","q?titolo=&puntoinizio=&puntofine=&durata=&lenght=&difficulty=&accessodisabili=");
		return map;
	}

	/*********************************************************************************************/
	
	//Mapper
	private ItinerarioDTO convertEntityToDto(Itinerario itinerario) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
		itinerarioDTO = modelMapper.map(itinerario, ItinerarioDTO.class);
		
		//Mapping
		String username = itinerario.getUtente().getUsername();
		itinerarioDTO.setId_utente(username);
		itinerarioDTO.setDurata(timeToString(itinerario.getDurata()));
		return itinerarioDTO;
	}

	private Itinerario convertDtoToEntity(ItinerarioDTO itinerarioDTO) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		Itinerario itinerario = new Itinerario();
		itinerario = modelMapper.map(itinerarioDTO, Itinerario.class);
		
		//Mapping
		String username = itinerarioDTO.getId_utente();
		Optional<User> userOptional = this.userService.getUtente(username);

		User utente = null;
		if(!userOptional.isEmpty())
			utente = userOptional.get();
		itinerario.setUtente(utente);
		
		try {
			itinerario.setDurata(stringToTime(itinerarioDTO.getDurata()));
		} catch (ParseException e) {
			throw new RequestApiException("Durata non valida.", HttpStatus.BAD_REQUEST);
		}
		
		return itinerario;
	}
	
	private Time stringToTime(String t) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Time time = new Time(formatter.parse(t).getTime());
		return time;
	}
	
	private String timeToString(Time t) {
		return t.toString();
	}

	/*********************************************************************************************/
}
