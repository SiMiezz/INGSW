package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Service.Interface.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    @Qualifier("mainRestaurantService")
    private IRestaurantService restaurantService;

    @GetMapping("/get/{name}")
    public Restaurant getByName(@PathVariable String name){
        Optional<Restaurant> restaurant = restaurantService.getByName(name);

        if(restaurant.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return restaurant.get();
    }
}
