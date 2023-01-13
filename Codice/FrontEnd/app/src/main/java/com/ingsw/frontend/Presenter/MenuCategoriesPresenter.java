package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.CategoryService;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuCategoriesPresenter {

    private final MenuCategoriesFragment menuCategoriesFragment;
    private CategoryService categoryService;

    // CONSTRUCTOR

    public MenuCategoriesPresenter(MenuCategoriesFragment menuCategoriesFragment) {
        this.menuCategoriesFragment = menuCategoriesFragment;
        categoryService = new CategoryService();
    }
    

    // GETTER AND SETTER

    public MenuCategoriesFragment getMenuCategoriesFragment() {
        return menuCategoriesFragment;
    }

    public void getByMenuId(Integer id){
        categoryService.getByMenuId(new Callback(){
            @Override
            public void returnResult(Object o) {
                ArrayList<Category> categoryList = (ArrayList<Category>) o;

                menuCategoriesFragment.loadCategory(categoryList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },id);
    }

}
