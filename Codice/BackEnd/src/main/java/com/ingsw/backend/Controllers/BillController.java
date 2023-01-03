package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.BillDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
    private BillDAO billDAO = new BillDAO();
}
