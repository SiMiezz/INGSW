package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.BillDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
    private BillDAO billDAO = new BillDAO();
}
