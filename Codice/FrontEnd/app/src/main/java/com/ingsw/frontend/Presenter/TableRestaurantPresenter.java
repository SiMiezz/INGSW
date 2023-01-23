package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.TableRestaurantService;
import com.ingsw.frontend.View.Adapter.TableRestaurantAdapter;
import com.ingsw.frontend.View.Fragment.TablesAllFragment;
import com.ingsw.frontend.View.Fragment.TablesNumberFragment;

import java.util.ArrayList;

public class TableRestaurantPresenter {
    
    private final TablesAllFragment tablesAllFragment;
    private final TablesNumberFragment tablesNumberFragment;
    private TableRestaurantService tableRestaurantService;

    
    public TableRestaurantPresenter(TablesAllFragment tablesAllFragment, TablesNumberFragment tablesNumberFragment) {
        this.tablesAllFragment = tablesAllFragment;
        this.tablesNumberFragment = tablesNumberFragment;
        tableRestaurantService = new TableRestaurantService();
    }

    public TablesAllFragment getTablesAllFragment() {
        return tablesAllFragment;
    }

    public TablesNumberFragment getTablesNumberFragment() {
        return tablesNumberFragment;
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


    public void countTotalTableByRestaurantName(String restaurantName){
        tableRestaurantService.countTotalTableByRestaurantName(new Callback(){

            @Override
            public void returnResult(Object o) {
                Integer result = (Integer) o;
                tablesNumberFragment.setTotalNumber(result);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, restaurantName);
    }


}
