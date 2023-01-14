package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Element;
import com.ingsw.backend.Service.Interface.IElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/element")
public class ElementController {

    @Autowired
    @Qualifier("mainElementService")
    private IElementService elementService;

    @PostMapping("/create")
    public Element create(@RequestBody Element element){
        return elementService.create(element);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        boolean delete = elementService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public List<Element> getByCategoryId(@PathVariable Integer id){
        return elementService.getByCategoryId(id);
    }

    @GetMapping("/get/{restaurantName}/{idCategory}")
    public List<Element> getByCategoryAndRestaurantId(@PathVariable String restaurantName, @PathVariable Integer idCategory){
        return elementService.getByCategoryIdAndRestaurantId(restaurantName, idCategory);
    }

}
