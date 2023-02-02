package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.OpenFoodService;
import com.ingsw.frontend.View.Dialog.ElementCreateDialog;

import java.util.ArrayList;

public class OpenFoodPresenter {

    private ElementCreateDialog elementCreateDialog;

    private OpenFoodService openFoodService;

    public OpenFoodPresenter(ElementCreateDialog elementCreateDialog) {
        this.elementCreateDialog = elementCreateDialog;
        openFoodService = new OpenFoodService();
    }

    public void getProductList(String name){
        openFoodService.getProductName(new Callback(){
            @Override
            public void returnResult(Object o) {
                /*ArrayList<String> productNameList = (ArrayList<String>) o;

                for (String string: productNameList) {
                    System.out.println(string);
                }
                elementCreateDialog.loadProductNames(productNameList);*/
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },name);
    }

    public void getProduct(String name){
        openFoodService.getProductName(new Callback(){
            @Override
            public void returnResult(Object o) {
                String string = (String) o;

                System.out.println("PROVAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + string);
                //elementCreateDialog.loadProductNames(productNameList);*/
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },name);
    }
}
