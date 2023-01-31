package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.OrderService;
import com.ingsw.frontend.View.Fragment.TablesSelectedFragment;

import java.util.ArrayList;

public class OrderPresenter {

    private OrderService orderService;
    private TablesSelectedFragment tablesSelectedFragment;

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
                System.out.println(e);
            }
        }, tableId);
    }

    public void sumPriceByTableId(Integer tableId){
        orderService.sumPriceByTableId(new Callback(){
            @Override
            public void returnResult(Object o) {
                Double sum = (Double) o;

                if(sum>0){
                    tablesSelectedFragment.openDialog(sum);
                    deleteByTableId(tableId);
                }
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, tableId);
    }

    public void deleteByTableId(Integer tableId){
        orderService.getByTableRestaurantId(new Callback(){
            @Override
            public void returnResult(Object o) {
                ArrayList<Order> orderArrayList = (ArrayList<Order>) o;

                for (Order order: orderArrayList) {
                    delete(order);
                }
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, tableId);
    }

    public void delete(Order order) {
        orderService.delete(new Callback(){

            @Override
            public void returnResult(Object o) {
                getOrdersByTableId(order.getTableId());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, order);
    }

    public void create(Order order) {
        orderService.create(new Callback(){

            @Override
            public void returnResult(Object o) {
                getOrdersByTableId(order.getTableId());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, order);
    }
}
