package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Repository.RestaurantRepository;
import com.ingsw.backend.Service.Interface.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainRestaurantService")
public class RestaurantService implements IRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantService(){}

    @Override
    public Optional<Restaurant> getByName(String name){
        return restaurantRepository.findById(name);
    }
}
