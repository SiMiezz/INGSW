package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Service.Class.ElementService;
import com.ingsw.frontend.View.Fragment.MenuElementsFragment;
import com.ingsw.frontend.Service.Callback;
import java.util.ArrayList;

public class MenuElementsPresenter {
    
    private final MenuElementsFragment menuElementsFragment;
    private ElementService elementService;

    public MenuElementsPresenter(MenuElementsFragment menuElementsFragment) {
        this.menuElementsFragment = menuElementsFragment;
        elementService = new ElementService();
    }
    
    
    // GETTER AND SETTER
    public MenuElementsFragment getMenuElementsFragment() {
        return menuElementsFragment;
    }

    public void getByCategoryId(Integer id){
        elementService.getByCategoryId(new Callback(){
            @Override
            public void returnResult(Object o) {
                ArrayList<Element> elementList = (ArrayList<Element>) o;
                menuElementsFragment.loadElement(elementList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },id);
    }

    public void deleteById(Integer id) {
        elementService.deleteById(new Callback(){

            @Override
            public void returnResult(Object o) {
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },id);
    }
}
