package com.ingsw.backend.Controllers;

import com.ingsw.backend.Model.*;
import com.ingsw.backend.Model.DTO.ElementDTO;
import com.ingsw.backend.Model.DTO.OrderDTO;
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

    @GetMapping("/sum/{id}")
    public Double sumPriceByTableId(@PathVariable Integer id){
        if(orderService.countByTableId(id) > 0){
            return orderService.sumPriceByTableId(id);
        }

        return 0.0;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody OrderDTO orderDTO){
        Order order = this.convertEntity(orderDTO);

        orderService.delete(order);
    }

    @GetMapping("/count/element/{id}/{from}/{to}")
    public Integer getCountElementOrdered(@PathVariable Integer id, @PathVariable Date from, @PathVariable Date to){
        Integer result = 0;

        result = orderService.getCountElementOrdered(id, from, to);

        return result;
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

        List<ElementDTO> elementDTOS = new ArrayList<>();

        if(order.getElementOrderList() != null){
            for (Element element:order.getElementOrderList()) {
                elementDTOS.add(convertElementDTO(element));
            }
        }

        orderDTO.setElements(elementDTOS);

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

        List<Element> elementList = new ArrayList<>();

        if(orderDTO.getElements() != null){
            for (ElementDTO elementDTO : orderDTO.getElements()) {
                elementList.add(convertElementEntity(elementDTO));
            }
        }

        if(!tableRestaurantOptional.isEmpty()){
            order.setTable(tableRestaurantOptional.get());
        }
        order.setElementOrderList(elementList);

        return order;
    }

    private ElementDTO convertElementDTO(Element element) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        ElementDTO elementDTO = new ElementDTO();
        elementDTO = modelMapper.map(element, ElementDTO.class);

        return elementDTO;
    }

    private Element convertElementEntity(ElementDTO elementDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Element element = new Element();
        element = modelMapper.map(elementDTO, Element.class);

        return element;
    }
}
