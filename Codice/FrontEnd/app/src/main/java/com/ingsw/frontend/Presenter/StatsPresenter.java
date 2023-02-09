package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.ElementService;
import com.ingsw.frontend.Service.Class.OrderService;
import com.ingsw.frontend.View.Fragment.RestaurantStatsFragment;

import java.sql.Date;
import java.util.ArrayList;

public class StatsPresenter {

    private RestaurantStatsFragment restaurantStatsFragment;
    private ElementService elementService;
    private OrderService orderService;

    public StatsPresenter(RestaurantStatsFragment restaurantStatsFragment){
        this.restaurantStatsFragment = restaurantStatsFragment;
        elementService = new ElementService();
        orderService = new OrderService();
    }

    public void getElementByMenuId(Integer id){
        elementService.getElementByMenuId(new Callback() {
            @Override
            public void returnResult(Object o) {
                ArrayList<Element> elements = (ArrayList<Element>) o;
                restaurantStatsFragment.setElements(elements, restaurantStatsFragment.getFromDate(), restaurantStatsFragment.getToDate());
            }

            @Override
            public void returnError(Throwable e) {

            }
        }, id);
    }

    public void getQuantityStats(ArrayList<Element> elements, Date fromDate, Date toDate){
        for (Element element : elements){
            orderService.getCountElementOrderedStats(new Callback() {
                @Override
                public void returnResult(Object o) {
                    Integer resultCount = (Integer) o;
                    element.setQuantityStats(resultCount);
                    System.out.println(o);
                }

                @Override
                public void returnError(Throwable e) {
                    element.setQuantityStats(0);
                }
            }, element.getId(), fromDate, toDate);
        }
    }



}
