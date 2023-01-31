package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.TableRestaurantService;
import com.ingsw.frontend.View.Adapter.TableRestaurantAdapter;
import com.ingsw.frontend.View.Fragment.TablesAllFragment;
import com.ingsw.frontend.View.Fragment.TablesNumberFragment;
import com.ingsw.frontend.View.Fragment.TablesSelectedFragment;

import java.util.ArrayList;

import okhttp3.Call;

public class TableRestaurantPresenter {
    
    private TablesAllFragment tablesAllFragment;
    private TablesNumberFragment tablesNumberFragment;
    private TableRestaurantService tableRestaurantService;
    private TablesSelectedFragment tablesSelectedFragment;

    private OrderPresenter orderPresenter;
    
    public TableRestaurantPresenter(TablesAllFragment tablesAllFragment, TablesNumberFragment tablesNumberFragment, TablesSelectedFragment tablesSelectedFragment) {
        this.tablesAllFragment = tablesAllFragment;
        this.tablesNumberFragment = tablesNumberFragment;
        this.tablesSelectedFragment = tablesSelectedFragment;
        orderPresenter = new OrderPresenter(tablesSelectedFragment);
        tableRestaurantService = new TableRestaurantService();
    }

    public void setTablesAllFragment(TablesAllFragment tablesAllFragment) {
        this.tablesAllFragment = tablesAllFragment;
    }

    public void setTablesNumberFragment(TablesNumberFragment tablesNumberFragment) {
        this.tablesNumberFragment = tablesNumberFragment;
    }

    public TablesAllFragment getTablesAllFragment() {
        return tablesAllFragment;
    }

    public TablesNumberFragment getTablesNumberFragment() {
        return tablesNumberFragment;
    }

    public void update(TableRestaurant tableRestaurant){
        tableRestaurantService.update(new Callback() {
            @Override
            public void returnResult(Object o) {
                getByRestaurantName(tableRestaurant.getRestaurantName());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },tableRestaurant);
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


    public void countTotalByRestaurantName(String restaurantName){
        tableRestaurantService.countTotalByRestaurantName(new Callback(){

            @Override
            public void returnResult(Object o) {
                Integer result = (Integer) o;

                tablesNumberFragment.setTotalNumber(result);
                countByRestaurantNameAndFree(restaurantName,false);
                countByRestaurantNameAndFree(restaurantName,true);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, restaurantName);
    }

    public void countByRestaurantNameAndFree(String restaurantName, boolean free){
        tableRestaurantService.countByRestaurantNameAndFree(new Callback(){

            @Override
            public void returnResult(Object o) {
                Integer result = (Integer) o;

                if(free){
                    tablesNumberFragment.setFreeNumber(result);
                }
                else{
                    tablesNumberFragment.setOccupiedNumber(result);
                }
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        }, restaurantName,free);
    }

    public void getById(Integer id) {
        tableRestaurantService.getById(new Callback(){

            @Override
            public void returnResult(Object o) {
                TableRestaurant result = (TableRestaurant) o;
                tablesSelectedFragment.setSeatsNumber(result);
                tablesSelectedFragment.getOrderRecyclerView(result);
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },id);
    }

    public void updateById(Integer id) {
        tableRestaurantService.getById(new Callback(){

            @Override
            public void returnResult(Object o) {
                TableRestaurant table = (TableRestaurant) o;

                if(table.isFree()){
                    table.setFree(false);
                    update(table);
                }
                else{
                    orderPresenter.sumPriceByTableId(table.getId());
                    table.setFree(true);
                    update(table);
                }
                countTotalByRestaurantName(table.getRestaurantName());
            }

            @Override
            public void returnError(Throwable e) {
                System.out.println(e);
            }
        },id);
    }
}
