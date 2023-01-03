package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.ClientDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientDAO clientDAO = new ClientDAO();
}
