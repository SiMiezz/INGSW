package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Enumerations.Aliment_Type;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.CategoryService;
import com.ingsw.frontend.View.Dialog.OrderCreateDialog;
import com.ingsw.frontend.View.Fragment.MenuCategoriesDrinkFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFoodFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFragment;

import java.util.ArrayList;

public class MenuCategoriesPresenter {

    private MenuCategoriesFragment menuCategoriesFragment;
    private MenuCategoriesFoodFragment menuCategoriesFoodFragment;
    private MenuCategoriesDrinkFragment menuCategoriesDrinkFragment;
    private OrderCreateDialog orderCreateDialog;
    private CategoryService categoryService;

    // CONSTRUCTOR

    public MenuCategoriesPresenter(MenuCategoriesFragment menuCategoriesFragment) {
        this.menuCategoriesFragment = menuCategoriesFragment;
        categoryService = new CategoryService();
    }

    public MenuCategoriesPresenter(MenuCategoriesFoodFragment menuCategoriesFoodFragment) {
        this.menuCategoriesFoodFragment = menuCategoriesFoodFragment;
        categoryService = new CategoryService();
    }

    public MenuCategoriesPresenter(MenuCategoriesDrinkFragment menuCategoriesDrinkFragment) {
        this.menuCategoriesDrinkFragment = menuCategoriesDrinkFragment;
        categoryService = new CategoryService();
    }

    public MenuCategoriesPresenter(OrderCreateDialog orderCreateDialog){
        this.orderCreateDialog = orderCreateDialog;
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

                menuCategoriesFoodFragment.loadCategory(categoryList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },id);
    }

    public void getByMenuIdAndAliment(Integer id, Aliment_Type aliment_type){
        categoryService.getByMenuIdAndAliment(new Callback(){

            @Override
            public void returnResult(Object o) {
                ArrayList<Category> categoryArrayList = (ArrayList<Category>) o;

                if(aliment_type == Aliment_Type.valueOf("food"))
                    menuCategoriesFoodFragment.loadCategory(categoryArrayList);
                else
                    menuCategoriesDrinkFragment.loadCategory(categoryArrayList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, id, aliment_type);
    }

    public void delete(Category category) {
        categoryService.delete(new Callback(){

            @Override
            public void returnResult(Object o) {
                getByMenuIdAndAliment(category.getMenuId(),category.getAliment());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },category);
    }

    public void create(Category category){
        categoryService.create(new Callback() {
            @Override
            public void returnResult(Object o) {
                getByMenuIdAndAliment(category.getMenuId(),category.getAliment());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },category);
    }

    public void update(Category category){
        categoryService.update(new Callback() {
            @Override
            public void returnResult(Object o) {
                getByMenuIdAndAliment(category.getMenuId(),category.getAliment());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },category);
    }
}
