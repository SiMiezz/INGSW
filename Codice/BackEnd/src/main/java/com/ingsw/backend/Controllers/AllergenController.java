package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Class.AllergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allergen")
public class AllergenController {

    @Autowired
    @Qualifier("mainAllergenService")
    private AllergenService allergenService;

}
