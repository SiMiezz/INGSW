package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Class.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    @Qualifier("mainClientService")
    private ClientService clientService;
}
