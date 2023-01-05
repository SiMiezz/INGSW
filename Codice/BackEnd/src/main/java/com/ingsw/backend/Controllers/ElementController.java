package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Class.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/element")
public class ElementController {

    @Autowired
    @Qualifier("mainElementService")
    private ElementService elementService;
}
