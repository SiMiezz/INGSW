package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Order;
import com.ingsw.backend.Repository.OrderRepository;
import com.ingsw.backend.Service.Interface.IOrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainOrderService")
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;

    public OrderService(){}

    @Override
    public Order create(Order order){
        return orderRepository.save(order);
    }

    @Override
    public boolean deleteById(int id){
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isEmpty()){
            return false;
        }

        orderRepository.delete(optionalOrder.get());
        return true;
    }
}
