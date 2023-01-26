package com.ingsw.frontend.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Presenter.OrderPresenter;
import com.ingsw.frontend.Presenter.TableRestaurantPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Activity.HomeActivity;
import com.ingsw.frontend.View.Adapter.OrderAdapter;

import java.util.ArrayList;

public class TablesSelectedFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ArrayList<Order> orderArrayList;
    private TextView numberOfSeats;

    private TableRestaurantPresenter tableRestaurantPresenter;
    private OrderPresenter orderPresenter;
    private OrderAdapter orderAdapter;

    private ImageButton removeButton;
    private ImageButton addButton;
    private ImageButton backButton;
    private ImageButton confirmButton;
    private RecyclerView recyclerView;



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

        orderArrayList = new ArrayList<>();
        numberOfSeats = rootView.findViewById(R.id.table_selected_number_seats);
        removeButton = rootView.findViewById(R.id.remove_order_button);
        addButton = rootView.findViewById(R.id.add_order_button);
        backButton = rootView.findViewById(R.id.back_order_button);
        confirmButton = rootView.findViewById(R.id.confirm_order_button);
        recyclerView = rootView.findViewById(R.id.selected_table_order_listview);

        orderAdapter = new OrderAdapter(getContext(), orderArrayList);

        tableRestaurantPresenter = new TableRestaurantPresenter(null, null, this);
        orderPresenter = new OrderPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(orderAdapter);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(orderAdapter.getCurrentLayout() == -1){
                    orderAdapter.setCurrentLayout(-2);
                    orderAdapter.notifyDataSetChanged();
                }

                removeButton.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.VISIBLE);

                addButton.setVisibility(View.INVISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(orderAdapter.getCurrentLayout() == -2){
                    orderAdapter.setCurrentLayout(-1);
                    orderAdapter.notifyDataSetChanged();
                }

                backButton.setVisibility(View.INVISIBLE);
                removeButton.setVisibility(View.VISIBLE);

                confirmButton.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.VISIBLE);

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }

    public void getInfoTableFromClick(Integer id) {
        tableRestaurantPresenter.getById(id);
    }

    public void setSeatsNumber(TableRestaurant tableRestaurant) {
        numberOfSeats.setText(String.valueOf(tableRestaurant.getSeats()));
    }

    public void getOrderRecyclerView(TableRestaurant tableRestaurant){
        orderPresenter.getOrdersByTableId(tableRestaurant.getId());
    }

    public void loadOrders(ArrayList<Order> orderArrayList) {
        orderAdapter.clearList();
        orderAdapter.setOrderArrayList(orderArrayList);
        orderAdapter.notifyDataSetChanged();
    }
}