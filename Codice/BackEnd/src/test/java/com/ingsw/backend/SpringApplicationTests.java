package com.ingsw.backend;

import com.ingsw.backend.Controllers.CategoryController;
import com.ingsw.backend.Controllers.UserController;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SpringApplicationTests {

	@Autowired
	CategoryController categoryController;

	@Autowired
	UserController userController;

	@Test
	void testCategory14() {
		List<CategoryDTO> expected = new ArrayList<>();
		assertEquals(expected,categoryController.getByMenuIdAndAliment(2, Aliment_Type.valueOf("food")));
	}

	@Test
	void testCategory25() {
		List<CategoryDTO> expected = new ArrayList<>();
		assertEquals(expected,categoryController.getByMenuIdAndAliment(-1, Aliment_Type.valueOf("drink")));
	}

	@Test
	void testCategory36() {
		assertThrows(IllegalArgumentException.class,() -> categoryController.getByMenuIdAndAliment(0, Aliment_Type.valueOf("")));
	}

	@Test
	void testCategoryNO() {
		//assertThrows(IllegalArgumentException.class,() ->categoryController.getByMenuIdAndAliment("", Aliment_Type.valueOf("")));
	}

	@Test
	void testUser14() {
		assertThrows(ResponseStatusException.class,() -> userController.getByEmailAndPassword("","ok"));
	}

	@Test
	void testUser23() {
		assertThrows(ResponseStatusException.class,() -> userController.getByEmailAndPassword("admin",""));
	}

	@Test
	void testUser24() {
		UserDTO expected = new UserDTO("admin","rest","ok","admin","admin", User_Type.valueOf("admin"));
		UserDTO actual = userController.getByEmailAndPassword("admin","ok");

		assertEquals(expected.getEmail(),actual.getEmail());
		assertEquals(expected.getPwd(),actual.getPwd());
		assertEquals(expected.getName(),actual.getName());
		assertEquals(expected.getSurname(),actual.getSurname());
		assertEquals(expected.getJob(),actual.getJob());
		assertEquals(expected.getRestaurantName(),actual.getRestaurantName());
	}

	@Test
	void testUserNO() {
		//assertThrows(IllegalArgumentException.class,() -> userController.getByEmailAndPassword(1,2));
	}

}
