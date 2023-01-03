package com.natour.Server.Utils;

import java.util.List;
import java.util.Optional;

import com.natour.Server.Model.User;

public interface IUserService {

	//Methods GET
	List<User> getAllUser();
	Optional<User> getUtente(String username);
	
	//Methods POST
	boolean creaUtente(User utente);
	
	//Methods PUT
	boolean modificaUtente(User utente);
	
	//Methods DELETE
	boolean deleteUtente(String username);
}
