package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.Order;
import com.ingsw.backend.Service.Interface.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    @Qualifier("mainOrderService")
    private IOrderService orderService;

    @PostMapping("/create")
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        boolean delete = orderService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public List<Order> getByTableRestaurantId(@PathVariable Integer id){
        return orderService.getByTableRestaurantId(id);
    }
}
