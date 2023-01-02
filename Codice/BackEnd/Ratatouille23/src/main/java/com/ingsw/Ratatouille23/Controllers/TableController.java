package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.TableDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table")
public class TableController {
    private TableDAO tableDAO = new TableDAO();
}
