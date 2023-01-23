package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.TableRestaurantService;
import com.ingsw.frontend.View.Adapter.TableRestaurantAdapter;
import com.ingsw.frontend.View.Fragment.TablesAllFragment;

import java.util.ArrayList;

public class TableRestaurantPresenter {
    
    private final TablesAllFragment tablesAllFragment;
    private TableRestaurantService tableRestaurantService;
    
    public TableRestaurantPresenter(TablesAllFragment tablesAllFragment) {
        this.tablesAllFragment = tablesAllFragment;
        tableRestaurantService = new TableRestaurantService();
    }

    public TablesAllFragment getTablesAllFragment() {
        return tablesAllFragment;
    }



    public void getByRestaurantName(String restaurantName){
        tableRestaurantService.getByRestaurantName(new Callback() {
            @Override
            public void returnResult(Object o) {
                ArrayList<TableRestaurant> tableRestaurantArrayList = (ArrayList<TableRestaurant>) o;
                tablesAllFragment.loadTableRestaurant(tableRestaurantArrayList);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },restaurantName);
    }

}
