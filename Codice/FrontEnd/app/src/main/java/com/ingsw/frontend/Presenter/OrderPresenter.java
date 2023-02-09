package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.CategoryService;
import com.ingsw.frontend.Service.Class.ElementService;
import com.ingsw.frontend.Service.Class.OrderService;
import com.ingsw.frontend.View.Dialog.OrderCreateDialog;
import com.ingsw.frontend.View.Fragment.TablesSelectedFragment;

import java.util.ArrayList;

public class OrderPresenter {

    private TablesSelectedFragment tablesSelectedFragment;
    private OrderCreateDialog orderCreateDialog;

    private OrderService orderService;
    private CategoryService categoryService;
    private ElementService elementService;

    public OrderPresenter(TablesSelectedFragment tablesSelectedFragment){
        this.tablesSelectedFragment = tablesSelectedFragment;
        orderService = new OrderService();
    }

    public OrderPresenter(OrderCreateDialog orderCreateDialog){
        this.orderCreateDialog = orderCreateDialog;
        categoryService = new CategoryService();
        elementService = new ElementService();
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

    public OrderCreateDialog getOrderCreateDialog() {
        return orderCreateDialog;
    }

    public void setOrderCreateDialog(OrderCreateDialog orderCreateDialog) {
        this.orderCreateDialog = orderCreateDialog;
    }

    public void getCategoryByMenuIdOrderByAlimentAndPosition(Integer id){
        categoryService.getCategoryByMenuIdOrderByAlimentAndPosition(new Callback(){

            @Override
            public void returnResult(Object o) {
                ArrayList<Category> categoryArrayList = (ArrayList<Category>) o;
                orderCreateDialog.loadCategory(categoryArrayList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, id);
    }

    public void getElementByMenuId(Integer id){
        elementService.getElementByMenuId(new Callback(){

            @Override
            public void returnResult(Object o) {
                ArrayList<Element> elementArrayList = (ArrayList<Element>) o;
                orderCreateDialog.loadElement(elementArrayList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, id);
    }
}
