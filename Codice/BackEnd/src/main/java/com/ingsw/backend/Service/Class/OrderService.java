package com.ingsw.backend.Service.Class;

import com.ingsw.backend.Model.Order;
import com.ingsw.backend.Repository.OrderRepository;
import com.ingsw.backend.Service.Interface.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mainOrderService")
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(){}

    @Override
    public void create(Order order){
        orderRepository.save(order);
    }

    @Override
    public List<Order> getByTablerestaurantId(Integer id){
        return orderRepository.findByTableId(id);
    }

    @Override
    public Double sumPriceByTableId(Integer id){
        return orderRepository.sumPriceByTableId(id);
    }

    @Override
    public Long countByTableId(Integer id) {
        return orderRepository.countByTableId(id);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
