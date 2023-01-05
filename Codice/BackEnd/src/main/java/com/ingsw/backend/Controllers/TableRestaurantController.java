package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Class.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table")
public class TableRestaurantController {

    @Autowired
    @Qualifier("mainTableRestaurantService")
    private TableRestaurantService tableRestaurantService;
}
