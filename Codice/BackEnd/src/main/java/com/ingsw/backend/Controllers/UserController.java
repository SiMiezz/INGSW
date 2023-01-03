package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.UserDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserDAO userDAO;

    public UserController(){
        userDAO = new UserDAO();
    }
}
