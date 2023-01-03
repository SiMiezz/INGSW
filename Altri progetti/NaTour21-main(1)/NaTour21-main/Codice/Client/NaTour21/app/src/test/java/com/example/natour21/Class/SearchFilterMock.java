package com.example.natour21.Class;

import com.example.natour21.Presenter.HomePagePresenter;

import java.util.List;
import java.util.Map;

public class SearchFilterMock {

    private HomePagePresenter test;

    public SearchFilterMock() {
        test = new HomePagePresenter();
    }

    public Map<String,String> getMap(List<String> l) throws IllegalArgumentException {
        return test.getFilerMap(l.get(0), l.get(1), l.get(2), l.get(3),
                                l.get(4),l.get(5),l.get(6),l.get(7));
    }
}
