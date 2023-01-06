package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Allergen;
import com.ingsw.backend.Service.Interface.IAllergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllergenController {

    @Autowired
    @Qualifier("mainAllergenService")
    private IAllergenService allergenService;

    @PostMapping("/allergen")
    public Allergen create(@RequestBody Allergen allergen){
        return allergenService.create(allergen);
    }

}
