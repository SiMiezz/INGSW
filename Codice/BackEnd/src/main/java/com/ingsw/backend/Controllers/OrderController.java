package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.DTO.OrderDTO;
import com.ingsw.backend.Model.Order;
import com.ingsw.backend.Model.TableRestaurant;
import com.ingsw.backend.Service.Interface.IOrderService;
import com.ingsw.backend.Service.Interface.ITableRestaurantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    @Qualifier("mainOrderService")
    private IOrderService orderService;

    @Autowired
    @Qualifier("mainTableRestaurantService")
    private ITableRestaurantService tableRestaurantService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public void create(@RequestBody OrderDTO orderDTO){
        Order order = this.convertEntity(orderDTO);

        orderService.create(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id){
        boolean delete = orderService.deleteById(id);

        if(!delete){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private Order convertEntity(OrderDTO orderDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Order order = new Order();
        order = modelMapper.map(orderDTO, Order.class);

        //Mapping
        Integer id = orderDTO.getTableId();
        Optional<TableRestaurant> tableRestaurantOptional = this.tableRestaurantService.getById(id);

        if(!tableRestaurantOptional.isEmpty()){
            order.setTable(tableRestaurantOptional.get());
        }

        return order;
    }

    //@GetMapping("/get/{id}")
    //public List<Order> getByTableRestaurantId(@PathVariable Integer id){
        //return orderService.getByTablerestaurantId(id);
    //}
}
