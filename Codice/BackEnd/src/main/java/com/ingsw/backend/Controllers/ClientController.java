package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.ClientDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientDAO clientDAO = new ClientDAO();
}
