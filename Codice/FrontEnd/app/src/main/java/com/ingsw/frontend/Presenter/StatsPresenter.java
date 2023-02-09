package com.ingsw.frontend.Presenter;

import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Service.Callback;
import com.ingsw.frontend.Service.Class.ElementService;
import com.ingsw.frontend.Service.Class.OrderService;
import com.ingsw.frontend.View.Fragment.RestaurantStatsFragment;

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
                restaurantStatsFragment.setElements(elements);
            }

            @Override
            public void returnError(Throwable e) {

            }
        }, id);
    }

    public void getQuantityOrdered(ArrayList<Element> elements){
        for (Element element : elements){
            orderService.getCountElementOrdered(new Callback() {
                @Override
                public void returnResult(Object o) {
                    Integer resultCount = (Integer) o;
                    element.setQuantityStats(resultCount);
                }

                @Override
                public void returnError(Throwable e) {

                }
            }, element.getId()); //vanno passate le due date
        }
    }



}
