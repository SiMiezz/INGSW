package com.ingsw.frontend.View.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Presenter.StatsPresenter;
import com.ingsw.frontend.R;

import java.util.ArrayList;

public class RestaurantStatsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button searchButton;
    private Button fromButton;
    private Button toButton;
    private BarChart barChart;

    private ArrayList<Element> elements;
    private StatsPresenter statsPresenter;
    private Intent intent;
    private Menu menu;

    public RestaurantStatsFragment() {
        // Required empty public constructor
    }

    public static RestaurantStatsFragment newInstance(String param1, String param2) {
        RestaurantStatsFragment fragment = new RestaurantStatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_restaurant_stats, container, false);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        searchButton = getView().findViewById(R.id.restaurant_stats_search_button);
        fromButton = getView().findViewById(R.id.restaurant_stats_from_button);
        toButton = getView().findViewById(R.id.restaurant_stats_to_button);
        barChart = getView().findViewById(R.id.bar_chart);

        statsPresenter = new StatsPresenter(this);

        elements = new ArrayList<>();

        intent = getActivity().getIntent();

        menu = (Menu) intent.getSerializableExtra("menu");

        statsPresenter.getElementByMenuId(menu.getId());

        System.out.println("##################");

        for(Element element : elements){
            statsPresenter.getQuantityOrdered(element, element.getId());
        }


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog fromDatePicker = new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                fromDatePicker.show();
            }
        });

        toButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog fromDatePicker = new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                fromDatePicker.show();
            }
        });


    }


    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
        System.out.println("@@@@@@@@@@@@@@@@@@@@");
    }
}