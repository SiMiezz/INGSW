package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Interface.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    @Qualifier("mainStatsService")
    private IStatsService statsService;
}
