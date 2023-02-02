package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.OpenFoodService;
import com.ingsw.frontend.View.Dialog.ElementCreateDialog;

import java.util.ArrayList;
import java.util.List;

public class OpenFoodPresenter {

    private ElementCreateDialog elementCreateDialog;

    private OpenFoodService openFoodService;

    public OpenFoodPresenter(ElementCreateDialog elementCreateDialog) {
        this.elementCreateDialog = elementCreateDialog;
        this.openFoodService = new OpenFoodService();
    }

    public void getProductList(String name){
        openFoodService.getProductList(new Callback() {
            @Override
            public void returnResult(Object o) {
                String string = (String) o;
                ArrayList<String> productNameList = new ArrayList<>();

                int inizio, fine, i=0;

                while(i<string.length()){

                    if (string.charAt(i) == '[') {
                        i++;
                        while(string.charAt(i) != ']'){
                            while(string.charAt(i) != ':') {
                                i++;
                            }
                            inizio = i + 2;

                            while(string.charAt(i) != '}') {
                                i++;
                            }
                            fine = i - 2;

                            if(fine>inizio){
                                String subStr = string.substring(inizio, fine+1);
                                productNameList.add(subStr);
                            }
                            else{
                                break;
                            }

                            i++;
                        }
                        break;
                    }

                    i++;

                }

                elementCreateDialog.loadProductNames(productNameList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },name);
    }
}
