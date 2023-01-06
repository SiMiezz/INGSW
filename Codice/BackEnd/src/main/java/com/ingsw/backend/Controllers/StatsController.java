package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Stats;
import com.ingsw.backend.Service.Interface.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class StatsController {

    @Autowired
    @Qualifier("mainStatsService")
    private IStatsService statsService;

    @GetMapping("/stats/{name}")
    public Stats getByRestaurantName(@PathVariable String name){
        Optional<Stats> stats = statsService.getByRestaurantName(name);

        if(stats.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return stats.get();
    }
}
