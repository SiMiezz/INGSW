package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.AllergenDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allergen")
public class AllergenController {
    private AllergenDAO allergenDAO;

    public AllergenController(){
        allergenDAO = new AllergenDAO();
    }

}
