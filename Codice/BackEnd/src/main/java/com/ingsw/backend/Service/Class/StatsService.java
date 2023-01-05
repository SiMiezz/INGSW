package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Model.Stats;
import com.ingsw.backend.Repository.StatsRepository;
import com.ingsw.backend.Service.Interface.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainStatsService")
public class StatsService implements IStatsService {

    @Autowired
    private StatsRepository statsRepository;

    public StatsService(){}

    @Override
    public Optional<Stats> getByRestaurant(Restaurant restaurant){
        return statsRepository.findByRestaurant(restaurant);
    }
}
