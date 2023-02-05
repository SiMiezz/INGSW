package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.DTO.MenuDTO;
import com.ingsw.backend.Model.Menu;
import com.ingsw.backend.Service.Interface.IMenuService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    @Qualifier("mainMenuService")
    private IMenuService menuService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/get/{name}")
    public MenuDTO getByRestaurantName(@PathVariable String name){
        Optional<Menu> menu = menuService.getByRestaurantName(name);

        if(menu.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        MenuDTO menuDTO = convertDTO(menu.get());

        return menuDTO;
    }

    private MenuDTO convertDTO(Menu menu) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        MenuDTO menuDTO = new MenuDTO();
        menuDTO = modelMapper.map(menu, MenuDTO.class);

        String restaurant_name = menu.getRestaurant().getName();
        menuDTO.setRestaurantName(restaurant_name);
        return menuDTO;
    }
}
