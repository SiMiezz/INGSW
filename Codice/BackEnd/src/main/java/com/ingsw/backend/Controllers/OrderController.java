package com.ingsw.backend.Controllers;

import com.ingsw.backend.DAO.OrderDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderDAO orderDAO = new OrderDAO();
}
