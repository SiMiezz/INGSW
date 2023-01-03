package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.ElementDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/element")
public class ElementController {
    private ElementDAO elementDAO = new ElementDAO();
}
