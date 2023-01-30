package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Presenter.TableRestaurantPresenter;
import com.ingsw.frontend.R;

import org.w3c.dom.Text;

public class TablesNumberFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView totalNumber;
    private TextView freeNumber;
    private TextView occupiedNumber;

    private TableRestaurantPresenter tableRestaurantPresenter;
    private Intent intent;
    private Restaurant restaurant;

    public TablesNumberFragment() {
        // Required empty public constructor
    }

    public static TablesNumberFragment newInstance(String param1, String param2) {
        TablesNumberFragment fragment = new TablesNumberFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_tables_number, container, false);

        totalNumber = rootView.findViewById(R.id.total_number_tables);
        freeNumber = rootView.findViewById(R.id.free_number_tables);
        occupiedNumber = rootView.findViewById(R.id.occupied_number_tables);

        tableRestaurantPresenter = new TableRestaurantPresenter(null,this, null);

        intent = getActivity().getIntent();

        restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        countTables(restaurant.getName());

        return rootView;
    }

    public void setTotalNumber(Integer total) {
        totalNumber.setText(String.valueOf(total));
    }

    public void setFreeNumber(Integer total) {
        freeNumber.setText(String.valueOf(total));
    }

    public void setOccupiedNumber(Integer total) {
        occupiedNumber.setText(String.valueOf(total));
    }

    public void countTables(String restaurantName){
        tableRestaurantPresenter.countTotalByRestaurantName(restaurantName);
    }
}