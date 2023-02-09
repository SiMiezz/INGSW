package com.ingsw.frontend.View.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Presenter.StatsPresenter;
import com.ingsw.frontend.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    private ArrayList<BarEntry> barEntryArrayList;
    private BarDataSet barDataSet;
    private BarData barData;

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
        barEntryArrayList = new ArrayList<>();

        statsPresenter = new StatsPresenter(this);

        elements = new ArrayList<>();

        intent = getActivity().getIntent();

        menu = (Menu) intent.getSerializableExtra("menu");

        statsPresenter.getElementByMenuId(menu.getId());



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createChart(elements);
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


    public void setElements(ArrayList<Element> elementArrayList) {
        elements = elementArrayList;

//        for(Element element : elements){
//            statsPresenter.getQuantityOrdered(element, element.getId());
//        }

        statsPresenter.getQuantityOrdered(elements); //vanno aggiunti i parametri per la data

//        createChart(elements);
    }

    public void createChart(ArrayList<Element> elementArrayList) {

        Collections.sort(elementArrayList, new Comparator<Element>() {
            @Override
            public int compare(Element element1, Element element2) {
                if(element1.getQuantityStats() > element2.getQuantityStats())
                    return -1;
                else if(element1.getQuantityStats() < element2.getQuantityStats())
                    return 1;
                else
                    return 0;
            }
        });

        int i = 0;

        for(Element element : elementArrayList){
            if(element.getQuantityStats() > 0){
                barEntryArrayList.add(new BarEntry(i, element.getQuantityStats(), element));
                i += 1;
            }
        }



        barDataSet = new BarDataSet(barEntryArrayList, "11111111");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(20f);
        barDataSet.setValueFormatter(new DefaultValueFormatter(0));

        barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.animateY(2000);
        barChart.animateY(2000);
        barChart.getDescription().setText("");

        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(2);


        ArrayList<String> elementNames = new ArrayList<>();
        for(Element element : elementArrayList)
            elementNames.add(element.getName().toUpperCase());

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(elementNames));
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);


    }



}