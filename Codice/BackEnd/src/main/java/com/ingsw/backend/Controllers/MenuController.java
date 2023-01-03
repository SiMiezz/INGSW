package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.MenuDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private MenuDAO menuDAO = new MenuDAO();
}
