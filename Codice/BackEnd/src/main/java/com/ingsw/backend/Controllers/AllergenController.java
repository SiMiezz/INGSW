package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Allergen;
import com.ingsw.backend.Service.Interface.IAllergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allergen")
public class AllergenController {

    @Autowired
    @Qualifier("mainAllergenService")
    private IAllergenService allergenService;

    @PostMapping("/create")
    public Allergen create(@RequestBody Allergen allergen){
        return allergenService.create(allergen);
    }

    @GetMapping("/get")
    public List<Allergen> getAll(){
        return allergenService.getAll();
    }

}
