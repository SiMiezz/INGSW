package com.ingsw.frontend.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Model.TableRestaurant;
import com.ingsw.frontend.Presenter.OrderPresenter;
import com.ingsw.frontend.Presenter.TableRestaurantPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.OrderAdapter;
import com.ingsw.frontend.View.Dialog.BillDialog;
import com.ingsw.frontend.View.Dialog.ErrorDialog;
import com.ingsw.frontend.View.Dialog.OrderCreateDialog;

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

    private TablesAllFragment tablesAllFragment;
    private TablesNumberFragment tablesNumberFragment;

    private ImageButton removeButton;
    private ImageButton addButton;
    private ImageButton backButton;
    private ImageButton confirmButton;
    private Button libera_occupaButton;
    private RecyclerView recyclerView;

    private Integer tableId;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public TablesAllFragment getTablesAllFragment() {
        return tablesAllFragment;
    }

    public void setTablesAllFragment(TablesAllFragment tablesAllFragment) {
        this.tablesAllFragment = tablesAllFragment;
    }

    public TablesSelectedFragment(TablesNumberFragment tablesNumberFragment) {
        this.tablesNumberFragment = tablesNumberFragment;
    }

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
        libera_occupaButton = rootView.findViewById(R.id.libera_occupa_button);
        recyclerView = rootView.findViewById(R.id.selected_table_order_listview);

        orderAdapter = new OrderAdapter(getContext(), orderArrayList);

        tableRestaurantPresenter = new TableRestaurantPresenter(null, tablesNumberFragment, this);
        orderPresenter = new OrderPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(orderAdapter);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!numberOfSeats.getText().equals("X")){
                    if(orderAdapter.getCurrentLayout() == -1){
                        orderAdapter.setCurrentLayout(-2);
                        orderAdapter.notifyDataSetChanged();
                    }

                    removeButton.setVisibility(View.INVISIBLE);
                    backButton.setVisibility(View.VISIBLE);

                    addButton.setVisibility(View.INVISIBLE);
                    confirmButton.setVisibility(View.VISIBLE);
                }
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
                if(getLibera_occupaButton().getText().equals("LIBERA")){
                    openCreateOrderDialog();
                }
                else if(getLibera_occupaButton().getText().equals("Libera/Occupa")){
                    ErrorDialog errorDialog = new ErrorDialog("You must select a table first!");
                    errorDialog.show(requireActivity().getSupportFragmentManager(), "Table NOT Selected!");
                }
                else{
                    ErrorDialog errorDialog = new ErrorDialog("You must occupy this table first!");
                    errorDialog.show(requireActivity().getSupportFragmentManager(), "Table NOT Selected!");
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList temp = orderAdapter.getSelectedItemsArrayList();

                removeSelectedItems();

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

        libera_occupaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getTableId()!=null){
                    tableRestaurantPresenter.setTablesAllFragment(getTablesAllFragment());
                    tableRestaurantPresenter.updateById(getTableId());
                    orderAdapter.notifyDataSetChanged();
                }
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

    public void removeSelectedItems(){
        ArrayList<Order> orders = orderAdapter.getSelectedItemsArrayList();

        for(Order order : orders){
            orderPresenter.delete(order);
        }
    }

    public void openDialog(Double sum){
        BillDialog billDialog = new BillDialog(sum);
        billDialog.show(requireActivity().getSupportFragmentManager(),"Bill");
    }

    public void openCreateOrderDialog(){
        OrderCreateDialog orderCreateDialog = new OrderCreateDialog(this);
        orderCreateDialog.show(requireActivity().getSupportFragmentManager(), "Order");
    }

    public void createOrder(Order order) {
        orderPresenter.create(order);
    }

    public Button getLibera_occupaButton() {
        return libera_occupaButton;
    }

    public void setLibera_occupaButton(Button libera_occupaButton) {
        this.libera_occupaButton = libera_occupaButton;
    }

}