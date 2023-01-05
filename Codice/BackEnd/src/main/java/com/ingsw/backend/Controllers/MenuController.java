package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Class.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    @Qualifier("mainMenuService")
    private MenuService menuService;
}
