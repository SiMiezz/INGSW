package com.ingsw.frontend.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ingsw.frontend.Presenter.TableRestaurantPresenter;
import com.ingsw.frontend.R;

public class TablesSelectedFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView numberOfSeats;
    private TableRestaurantPresenter tableRestaurantPresenter;

    public TablesSelectedFragment() {
        // Required empty public constructor
    }

    public static TablesSelectedFragment newInstance(String param1, String param2) {
        TablesSelectedFragment fragment = new TablesSelectedFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_tables_selected, container, false);

        numberOfSeats = rootView.findViewById(R.id.table_selected_number_seats);

        tableRestaurantPresenter = new TableRestaurantPresenter(null, null, this);

        return rootView;
    }

    public void getInfoTableFromClick(Integer id) {
        tableRestaurantPresenter.getSeatsByTableRestaurantId(id);
    }

    public void setSeatsNumber(Integer result) {
        numberOfSeats.setText(String.valueOf(result));
    }
}