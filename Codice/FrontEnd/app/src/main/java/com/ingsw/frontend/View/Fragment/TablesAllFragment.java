package com.ingsw.frontend.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingsw.frontend.Model.Restaurant;
import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Presenter.TableRestaurantPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.TableRestaurantAdapter;

import java.util.ArrayList;

public class TablesAllFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TableRestaurantAdapter tableRestaurantAdapter;
    private ArrayList<TableRestaurant> tableRestaurantArrayList;
    private RecyclerView recyclerView;

    private Intent intent;
    private Restaurant restaurant;

    private TableRestaurantPresenter tableRestaurantPresenter;

    private TablesSelectedFragment tablesSelectedFragment;
    private TablesNumberFragment tablesNumberFragment;

    public TablesAllFragment() {
        // Required empty public constructor
    }

    public TablesAllFragment(TablesSelectedFragment tablesSelectedFragment, TablesNumberFragment tablesNumberFragment) {
        this.tablesSelectedFragment = tablesSelectedFragment;
        this.tablesNumberFragment = tablesNumberFragment;
    }

    public static TablesAllFragment newInstance(String param1, String param2) {
        TablesAllFragment fragment = new TablesAllFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_tables_all, container, false);

        tableRestaurantArrayList = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.tables_listview);

        tableRestaurantAdapter = new TableRestaurantAdapter(getContext(),tableRestaurantArrayList, tablesSelectedFragment);

        tableRestaurantPresenter = new TableRestaurantPresenter(this,tablesNumberFragment, tablesSelectedFragment);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(tableRestaurantAdapter);

        intent = getActivity().getIntent();

        restaurant = (Restaurant) intent.getSerializableExtra("restaurant");

        tableRestaurantPresenter.getByRestaurantName(restaurant.getName());

        return rootView;
    }



    public void loadTableRestaurant(ArrayList<TableRestaurant> tableRestaurantArrayList){
        tableRestaurantAdapter.clearList();
        tableRestaurantAdapter.setTableRestaurantArrayList(tableRestaurantArrayList);
        tableRestaurantAdapter.notifyDataSetChanged();
    }
}