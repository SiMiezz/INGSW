package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Menu;
import com.ingsw.backend.Service.Interface.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    @Qualifier("mainMenuService")
    private IMenuService menuService;

    @GetMapping("/get/{name}")
    public Menu getByRestaurantName(@PathVariable String name){
        Optional<Menu> menu = menuService.getByRestaurantName(name);

        if(menu.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return menu.get();
    }
}
