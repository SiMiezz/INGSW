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

                for (Element element: elementList) {
                    System.out.println(element.getName());
                }

                menuElementsFragment.loadElement(elementList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },id);
    }

    public void delete(Element element) {
        elementService.delete(new Callback(){

            @Override
            public void returnResult(Object o) {
                getByCategoryId(element.getCategoryId());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },element);
    }

    public void create(Element element){
        elementService.create(new Callback() {
            @Override
            public void returnResult(Object o) {
                getByCategoryId(element.getCategoryId());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },element);
    }
}
