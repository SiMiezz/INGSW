package com.ingsw.backend;

import com.ingsw.backend.Controllers.CategoryController;
import com.ingsw.backend.Controllers.UserController;
import com.ingsw.backend.Model.Category;
import com.ingsw.backend.Model.DTO.CategoryDTO;
import com.ingsw.backend.Model.DTO.UserDTO;
import com.ingsw.backend.Model.Enumerations.Aliment_Type;
import com.ingsw.backend.Model.Enumerations.User_Type;
import com.ingsw.backend.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringApplicationTests {

	@Autowired
	CategoryController categoryController;

	@Autowired
	UserController userController;

	@Test
	void testCategory_LegalId_LegalAliment_NotEmptyList() {
		List<CategoryDTO> expected = new ArrayList<>();

		expected.add(new CategoryDTO(7,1,"antipasti",Aliment_Type.valueOf("food"),0));
		expected.add(new CategoryDTO(3,1,"contorni",Aliment_Type.valueOf("food"),1));
		expected.add(new CategoryDTO(1,1,"primi",Aliment_Type.valueOf("food"),2));
		expected.add(new CategoryDTO(2,1,"secondi",Aliment_Type.valueOf("food"),3));
		expected.add(new CategoryDTO(9,1,"pizze",Aliment_Type.valueOf("food"),4));

		List<CategoryDTO> result = categoryController.getByMenuIdAndAliment(1, Aliment_Type.valueOf("food"));

		if(expected.size() != result.size())
			fail();
		else{
			for(int i = 0; i<expected.size(); i++){
				assertEquals(expected.get(i).getId(), result.get(i).getId());
				assertEquals(expected.get(i).getMenuId(),result.get(i).getMenuId());
				assertEquals(expected.get(i).getName(),result.get(i).getName());
				assertEquals(expected.get(i).getAliment(),result.get(i).getAliment());
				assertEquals(expected.get(i).getPosizione(),result.get(i).getPosizione());
			}
		}
	}

	@Test
	void testCategory_LegalId_LegalAliment_EmptyList() {
		List<CategoryDTO> expected = new ArrayList<>();
		List<CategoryDTO> result = categoryController.getByMenuIdAndAliment(-1,Aliment_Type.valueOf("food"));

		if(expected.size() != result.size())
			fail();
		else{
			for(int i = 0; i<expected.size(); i++){
				assertEquals(expected.get(i).getId(), result.get(i).getId());
				assertEquals(expected.get(i).getMenuId(),result.get(i).getMenuId());
				assertEquals(expected.get(i).getName(),result.get(i).getName());
				assertEquals(expected.get(i).getAliment(),result.get(i).getAliment());
				assertEquals(expected.get(i).getPosizione(),result.get(i).getPosizione());
			}
		}
	}

	@Test
	void testCategory_LegalId_IllegalAliment() {
		assertThrows(IllegalArgumentException.class,() -> categoryController.getByMenuIdAndAliment(0, Aliment_Type.valueOf("")));
	}

	@Test
	void testUser_IllegalEmail_LegalPassword() {
		assertThrows(ResponseStatusException.class,() -> userController.getByEmailAndPassword("","ok"));
	}

	@Test
	void testUser_LegalEmail_IllegalPassword() {
		assertThrows(ResponseStatusException.class,() -> userController.getByEmailAndPassword("admin",""));
	}

	@Test
	void testUser_LegalEmail_LegalPassword_NotEmptyUser() {
		UserDTO expected = new UserDTO("admin","rest","ok","admin","admin", User_Type.valueOf("admin"));
		UserDTO result = userController.getByEmailAndPassword("admin","ok");

		assertEquals(expected.getEmail(),result.getEmail());
		assertEquals(expected.getPwd(),result.getPwd());
		assertEquals(expected.getName(),result.getName());
		assertEquals(expected.getSurname(),result.getSurname());
		assertEquals(expected.getJob(),result.getJob());
		assertEquals(expected.getRestaurantName(),result.getRestaurantName());
	}

	@Test
	void testUser_LegalEmail_LegalPassword_EmptyUser(){
		assertThrows(ResponseStatusException.class, () -> userController.getByEmailAndPassword("notExistent","notExistent"));
	}

}
