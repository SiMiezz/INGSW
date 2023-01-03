package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.ElementDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/element")
public class ElementController {
    private ElementDAO elementDAO = new ElementDAO();
}
