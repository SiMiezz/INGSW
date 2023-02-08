package com.ingsw.frontend.View.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.ingsw.frontend.Model.Element;
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

        searchButton = rootView.findViewById(R.id.restaurant_stats_search_button);
        fromButton = rootView.findViewById(R.id.restaurant_stats_from_button);
        toButton = rootView.findViewById(R.id.restaurant_stats_to_button);
        barChart = rootView.findViewById(R.id.bar_chart);

        ArrayList<Element> elements = new ArrayList<>();
        //elements = presenter.getelements
        ArrayList<BarEntry> data = new ArrayList<>();




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

        return rootView;
    }
}