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
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
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

    @GetMapping("/get/table/{id}")
    public List<OrderDTO> getByTableRestaurantId(@PathVariable Integer id){
        List<Order> orderList = orderService.getByTablerestaurantId(id);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order: orderList) {
            orderDTOS.add(convertDTO(order));
        }

        return orderDTOS;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody OrderDTO orderDTO){
        Order order = this.convertEntity(orderDTO);

        orderService.delete(order);
    }

    private OrderDTO convertDTO(Order order) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO = modelMapper.map(order, OrderDTO.class);

        Integer table_id = order.getTable().getId();
        orderDTO.setTableId(table_id);

        Date date = order.getDatecreate();
        String strdate = date.toString();
        orderDTO.setDatecreate(strdate);

        return orderDTO;
    }

    private Order convertEntity(OrderDTO orderDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Order order = new Order();
        order = modelMapper.map(orderDTO, Order.class);

        //Mapping
        Integer id = orderDTO.getTableId();
        Optional<TableRestaurant> tableRestaurantOptional = this.tableRestaurantService.getById(id);

        String datestr = orderDTO.getDatecreate();
        Date date = Date.valueOf(datestr);
        order.setDatecreate(date);

        if(!tableRestaurantOptional.isEmpty()){
            order.setTable(tableRestaurantOptional.get());
        }

        return order;
    }
}
