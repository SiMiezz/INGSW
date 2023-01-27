package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.OrderService;
import com.ingsw.frontend.View.Fragment.TablesSelectedFragment;


import java.util.ArrayList;

public class OrderPresenter {

    private OrderService orderService;
    private final TablesSelectedFragment tablesSelectedFragment;

    public OrderPresenter(TablesSelectedFragment tablesSelectedFragment){
        this.tablesSelectedFragment = tablesSelectedFragment;
        orderService = new OrderService();
    }

    public void getOrdersByTableId(Integer tableId){
        orderService.getByTableRestaurantId(new Callback(){
            @Override
            public void returnResult(Object o) {
                ArrayList<Order> orderArrayList = (ArrayList<Order>) o;
                tablesSelectedFragment.loadOrders(orderArrayList);
            }

            @Override
            public void returnError(Throwable e) {

            }
        }, tableId);
    }


    public void delete(Order order) {
        orderService.delete(new Callback(){

            @Override
            public void returnResult(Object o) {

            }

            @Override
            public void returnError(Throwable e) {

            }
        }, order);
    }
}
