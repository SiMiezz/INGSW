package com.natour.Server.Controller;

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
import com.natour.Server.Model.User;
import com.natour.Server.Model.DTO.UserDTO;
import com.natour.Server.Utils.UtilsHeader;
import com.natour.Server.Utils.IUserService;

@RestController
@RequestMapping(path = "api/user", produces = { "application/json" })
public class UserController {

	@Autowired
	@Qualifier("mainUserService")
	private IUserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/*********************************************************************************************/

	//Get Mapping
	@GetMapping(path = "listaUtenti")
	@ResponseBody
	public List<User> getAll() {
		return this.userService.getAllUser();
	}

	@GetMapping(path = "getUtente/{username}")
	@ResponseBody
	public Optional<User> getUser(@PathVariable(name = "username") String username) {
		
		Optional<User> result = this.userService.getUtente(username);
		
		if(result.isEmpty())
			throw new RequestApiException("Utente non trovato.", HttpStatus.NOT_FOUND);
		return result;
	}

	@PostMapping(path = "getUtente/social")
	@ResponseBody
	public ResponseEntity<String> getUserSocial(@RequestBody UserDTO utente) {
		
		Optional<User> result = this.userService.getUtente(utente.getUsername());
		
		if(result.isEmpty()) {
			User newUser = convertDtoToEntity(utente);
			boolean creato = this.userService.creaUtente(newUser);
			if(creato) {
				UtilsHeader h = new UtilsHeader("Utente social salvato.");
				return ResponseEntity.status(HttpStatus.CREATED).headers(h.getHeaders()).build();
			}else
				throw new RequestApiException("Utente social non salvato.", HttpStatus.BAD_REQUEST);
		}
		UtilsHeader h = new UtilsHeader("Utente social già esistente.");
		return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
	}
	  
	//Post Mapping
	@PostMapping(path = "createUtente")
	@ResponseBody
	public ResponseEntity<String> createUser(@RequestBody UserDTO utente) {

		User newUser = convertDtoToEntity(utente);
		
		boolean creato = this.userService.creaUtente(newUser);
		if(creato) {
			UtilsHeader h = new UtilsHeader("Utente salvato.");
			return ResponseEntity.status(HttpStatus.CREATED).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Utente non salvato.", HttpStatus.BAD_REQUEST);
	}

	//Put Mapping
	@PutMapping(path = "modificaUtente")
	@ResponseBody
	public ResponseEntity<String> modifyUser(@RequestBody UserDTO utente) {

		User newUser = convertDtoToEntity(utente);
		
		boolean modificato = this.userService.modificaUtente(newUser);
		if(modificato) {
			UtilsHeader h = new UtilsHeader("Utente modificato.");
			return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Utente non salvato.", HttpStatus.BAD_REQUEST);
	}

	//Delete Mapping
	@DeleteMapping(path = "deleteUtente/{username}")
	@ResponseBody
	public ResponseEntity<String> deleteUser(@PathVariable(name = "username") String username) {

		boolean eliminato = this.userService.deleteUtente(username);
		if(eliminato) {
			UtilsHeader h = new UtilsHeader("Utente eliminato.");
			return ResponseEntity.status(HttpStatus.OK).headers(h.getHeaders()).build();
		}else
			throw new RequestApiException("Utente non eliminato.", HttpStatus.BAD_REQUEST);
	}

	/*********************************************************************************************/

	//Getter e Setter
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/*********************************************************************************************/

	//Mapper
	private UserDTO convertEntityToDto(User user) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		UserDTO userDTO = new UserDTO();
		userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	private User convertDtoToEntity(UserDTO userDTO) {
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		User user = new User();
		user = modelMapper.map(userDTO, User.class);
		return user;
	}

	/*********************************************************************************************/

}
