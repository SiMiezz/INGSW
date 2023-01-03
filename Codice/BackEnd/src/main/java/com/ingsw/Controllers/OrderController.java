package com.ingsw.Ratatouille23.Controllers;

import com.ingsw.Ratatouille23.DAO.OrderDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderDAO orderDAO = new OrderDAO();
}
