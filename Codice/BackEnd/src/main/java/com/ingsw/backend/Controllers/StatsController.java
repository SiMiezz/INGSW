package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.StatsDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {
    private StatsDAO statsDAO = new StatsDAO();
}
