package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.RestaurantDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private RestaurantDAO restaurantDAO = new RestaurantDAO();
}
