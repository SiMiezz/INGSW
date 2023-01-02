package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.StatsDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {
    private StatsDAO statsDAO = new StatsDAO();
}
