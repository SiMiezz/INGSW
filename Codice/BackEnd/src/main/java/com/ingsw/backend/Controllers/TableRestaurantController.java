package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.TableRestaurant;
import com.ingsw.backend.Service.Interface.ITableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tablerestaurant")
public class TableRestaurantController {

    @Autowired
    @Qualifier("mainTableRestaurantService")
    private ITableRestaurantService tableRestaurantService;

    @GetMapping("/{name}")
    public Iterable<TableRestaurant> getByRestaurantName(@PathVariable String name){
        return tableRestaurantService.getByRestaurantName(name);
    }
}
