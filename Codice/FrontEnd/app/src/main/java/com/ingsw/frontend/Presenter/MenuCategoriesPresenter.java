package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.View.Fragment.MenuCategoriesFragment;

public class MenuCategoriesPresenter {

    private final MenuCategoriesFragment menuCategoriesFragment;


    public MenuCategoriesPresenter(MenuCategoriesFragment menuCategoriesFragment) {
        this.menuCategoriesFragment = menuCategoriesFragment;
    }
    

    // GETTER AND SETTER
    public MenuCategoriesFragment getMenuCategoriesFragment() {
        return menuCategoriesFragment;
    }

}
