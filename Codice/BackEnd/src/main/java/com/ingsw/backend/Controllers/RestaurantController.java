package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.RestaurantDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private RestaurantDAO restaurantDAO = new RestaurantDAO();
}
