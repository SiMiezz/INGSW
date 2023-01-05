package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Class.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("mainUserService")
    private UserService userService;
}
