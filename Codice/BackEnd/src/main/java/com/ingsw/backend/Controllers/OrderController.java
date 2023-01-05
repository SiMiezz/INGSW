package com.ingsw.backend.Controllers;

import com.ingsw.backend.Service.Interface.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    @Qualifier("mainOrderService")
    private IOrderService orderService;
}
