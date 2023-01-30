package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.DTO.TableRestaurantDTO;
import com.ingsw.backend.Model.Restaurant;
import com.ingsw.backend.Model.TableRestaurant;
import com.ingsw.backend.Service.Interface.IRestaurantService;
import com.ingsw.backend.Service.Interface.ITableRestaurantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tablerestaurant")
public class TableRestaurantController {

    @Autowired
    @Qualifier("mainTableRestaurantService")
    private ITableRestaurantService tableRestaurantService;

    @Autowired
    @Qualifier("mainRestaurantService")
    private IRestaurantService restaurantService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/update")
    public void update(@RequestBody TableRestaurantDTO tableRestaurantDTO){
        TableRestaurant tableRestaurant = this.convertEntity(tableRestaurantDTO);

        tableRestaurantService.update(tableRestaurant);
    }

    @GetMapping("/get/{name}")
    public List<TableRestaurantDTO> getByRestaurantName(@PathVariable String name){
        List<TableRestaurant> tableRestaurantList = tableRestaurantService.getByRestaurantName(name);

        List<TableRestaurantDTO> tableRestaurantDTOS = new ArrayList<>();
        for (TableRestaurant tableRestaurant: tableRestaurantList) {
            tableRestaurantDTOS.add(convertDTO(tableRestaurant));
        }

        return tableRestaurantDTOS;
    }

    @GetMapping("/get/one/{id}")
    public TableRestaurantDTO getById(@PathVariable Integer id){
        Optional<TableRestaurant> optionalTableRestaurant = tableRestaurantService.getById(id);

        if(optionalTableRestaurant.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        TableRestaurantDTO tableRestaurantDTO = convertDTO(optionalTableRestaurant.get());

        return  tableRestaurantDTO;
    }

    @GetMapping("/count/total/{name}")
    public Long countByRestaurantName(@PathVariable String name){
        return tableRestaurantService.countByRestaurantName(name);
    }

    @GetMapping("/count/{name}/{free}")
    public Long countByRestaurantName(@PathVariable String name, @PathVariable boolean free){
        return tableRestaurantService.countByRestaurantNameAndFree(name,free);
    }

    private TableRestaurantDTO convertDTO(TableRestaurant tableRestaurant) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TableRestaurantDTO tableRestaurantDTO = new TableRestaurantDTO();
        tableRestaurantDTO = modelMapper.map(tableRestaurant, TableRestaurantDTO.class);

        String restaurant_name = tableRestaurant.getRestaurant().getName();
        tableRestaurantDTO.setRestaurantName(restaurant_name);

        return tableRestaurantDTO;
    }

    private TableRestaurant convertEntity(TableRestaurantDTO tableRestaurantDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TableRestaurant tableRestaurant = new TableRestaurant();
        tableRestaurant = modelMapper.map(tableRestaurantDTO, TableRestaurant.class);

        //Mapping
        String name = tableRestaurantDTO.getRestaurantName();
        Optional<Restaurant> restaurantOptional = this.restaurantService.getByName(name);

        if(!restaurantOptional.isEmpty()){
            tableRestaurant.setRestaurant(restaurantOptional.get());
        }

        return tableRestaurant;
    }
}
