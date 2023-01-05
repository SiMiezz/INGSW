package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Repository.RestaurantRepository;
import com.ingsw.backend.Service.Interface.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainRestaurantService")
public class RestaurantService implements IRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantService(){}
}
