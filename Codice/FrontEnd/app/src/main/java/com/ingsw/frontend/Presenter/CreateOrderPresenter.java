package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.CategoryService;
import com.ingsw.frontend.Service.Class.ElementService;
import com.ingsw.frontend.View.Dialog.OrderCreateDialog;

import java.util.ArrayList;

public class CreateOrderPresenter {

    private OrderCreateDialog orderCreateDialog;
    private CategoryService categoryService;
    private ElementService elementService;

    public CreateOrderPresenter(OrderCreateDialog orderCreateDialog){
        this.orderCreateDialog = orderCreateDialog;
        categoryService = new CategoryService();
        elementService = new ElementService();
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
