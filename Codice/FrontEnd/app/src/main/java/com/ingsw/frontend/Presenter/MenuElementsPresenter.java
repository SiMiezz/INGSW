package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.View.Fragment.MenuElementsFragment;

public class MenuElementsPresenter {
    
    private final MenuElementsFragment menuElementsFragment;
    

    public MenuElementsPresenter(MenuElementsFragment menuElementsFragment) {
        this.menuElementsFragment = menuElementsFragment;
    }
    
    
    // GETTER AND SETTER
    public MenuElementsFragment getMenuElementsFragment() {
        return menuElementsFragment;
    }
}
