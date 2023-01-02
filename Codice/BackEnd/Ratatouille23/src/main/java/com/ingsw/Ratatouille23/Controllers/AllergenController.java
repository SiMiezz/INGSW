package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.AllergenDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allergen")
public class AllergenController {
    private AllergenDAO allergenDAO = new AllergenDAO();
}
