package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Class.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    @Qualifier("mainRestaurantService")
    private RestaurantService restaurantService;
}
