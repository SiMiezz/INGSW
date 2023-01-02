package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.MenuDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private MenuDAO menuDAO = new MenuDAO();
}
