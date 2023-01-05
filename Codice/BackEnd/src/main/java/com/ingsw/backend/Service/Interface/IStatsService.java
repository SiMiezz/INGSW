package com.ingsw.backend.Service.Interface;

import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Model.Stats;

import java.util.Optional;

public interface IStatsService {

    public Optional<Stats> getByRestaurant(Restaurant restaurant);
}
