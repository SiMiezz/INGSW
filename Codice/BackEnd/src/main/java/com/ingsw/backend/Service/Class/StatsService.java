package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Repository.StatsRepository;
import com.ingsw.backend.Service.Interface.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainStatsService")
public class StatsService implements IStatsService {

    @Autowired
    private StatsRepository statsRepository;

    public StatsService(){}
}
