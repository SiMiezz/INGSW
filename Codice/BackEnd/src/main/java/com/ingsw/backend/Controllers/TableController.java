package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.TableDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table")
public class TableController {
    private TableDAO tableDAO = new TableDAO();
}
