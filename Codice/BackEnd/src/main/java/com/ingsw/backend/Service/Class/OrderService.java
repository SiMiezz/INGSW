package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Repository.OrderRepository;
import com.ingsw.backend.Service.Interface.IOrderService;
import org.springframework.stereotype.Service;

@Service("mainOrderService")
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;

    public OrderService(){}
}
