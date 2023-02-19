package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Presenter.OrderPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.CategoryAndElementCreateOrderAdapter;
import com.ingsw.frontend.View.Adapter.SelectedElementOrderAdapter;
import com.ingsw.frontend.View.Fragment.TablesSelectedFragment;

import java.util.ArrayList;

public class OrderCreateDialog extends AppCompatDialogFragment {

    private OrderCreateDialogListener orderCreateDialogListener;
    private TablesSelectedFragment tablesSelectedFragment;

    private RecyclerView allElementsRecyclerView;
    private RecyclerView selectedElementsRecyclerView;
    private OrderPresenter orderPresenter = new OrderPresenter(this);

    private CategoryAndElementCreateOrderAdapter categoryAndElementCreateOrderAdapter;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Element> elementArrayList;

    private SelectedElementOrderAdapter selectedElementOrderAdapter;

    private Intent intent;
    private Menu menu;

    private ImageButton removeButton;
    private ImageButton confirmButton;
    private ImageButton backButton;
    private View separatorView;

    private Button cancelButton;
    private Button okButton;

    public OrderCreateDialog(TablesSelectedFragment tablesSelectedFragment){
        this.tablesSelectedFragment = tablesSelectedFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.order_create_layout, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .show();


        // **************************************** seconda recycler view

        selectedElementsRecyclerView = view.findViewById(R.id.create_order_selected_elements);
        removeButton = view.findViewById(R.id.remove_selected_element_order_button);
        confirmButton = view.findViewById(R.id.confirm_selected_element_order_button);
        backButton = view.findViewById(R.id.back_selected_element_order_button);
        separatorView = view.findViewById(R.id.separator_view);

        ArrayList<Element> selectedElementArrayList = new ArrayList<>();

        selectedElementOrderAdapter = new SelectedElementOrderAdapter(getContext(), selectedElementArrayList);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        selectedElementsRecyclerView.setLayoutManager(linearLayoutManager1);
        selectedElementsRecyclerView.setAdapter(selectedElementOrderAdapter);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedElementOrderAdapter.getCurrentLayout() == -1){
                    selectedElementOrderAdapter.setCurrentLayout(-2);
                    selectedElementOrderAdapter.notifyDataSetChanged();
                }

                removeButton.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                separatorView.setVisibility(View.VISIBLE);

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedElementOrderAdapter.getCurrentLayout() == -2){
                    selectedElementOrderAdapter.setCurrentLayout(-1);
                    selectedElementOrderAdapter.notifyDataSetChanged();
                }

                confirmButton.setVisibility(View.INVISIBLE);
                removeButton.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.INVISIBLE);
                separatorView.setVisibility(View.INVISIBLE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Element element : selectedElementOrderAdapter.getToRemoveSelectedElementArrayList())
                    selectedElementOrderAdapter.getSelectedElementArrayList().remove(element);

                selectedElementOrderAdapter.getGroupedSelectedElementArrayList().clear();

                selectedElementOrderAdapter.notifyDataSetChanged();

            }
        });

        // **************************************** prima recycler view
        allElementsRecyclerView = view.findViewById(R.id.create_order_all_elements);

        categoryArrayList = new ArrayList<>();
        elementArrayList = new ArrayList<>();

        categoryAndElementCreateOrderAdapter = new CategoryAndElementCreateOrderAdapter(getContext(), categoryArrayList, elementArrayList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allElementsRecyclerView.setLayoutManager(linearLayoutManager);
        allElementsRecyclerView.setAdapter(categoryAndElementCreateOrderAdapter);

        intent = getActivity().getIntent();

        menu = (Menu) intent.getSerializableExtra("menu");

        orderPresenter.getCategoryByMenuIdOrderByAlimentAndPosition(menu.getId());
        orderPresenter.getElementByMenuId(menu.getId());

        // ****************************************

        cancelButton = view.findViewById(R.id.order_create_cancel_button);
        okButton = view.findViewById(R.id.order_create_ok_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order = new Order(computeTotalPrice(selectedElementOrderAdapter.getSelectedElementArrayList()), String.valueOf(java.time.LocalDate.now()), tablesSelectedFragment.getTableId(), selectedElementOrderAdapter.getSelectedElementArrayList());

                if(selectedElementOrderAdapter.getSelectedElementArrayList().size() > 0){
                    orderCreateDialogListener.createOrder(order, tablesSelectedFragment);
                    dialog.dismiss();
                }

            }
        });


        return dialog;
    }


    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);

        try {
            orderCreateDialogListener = (OrderCreateDialogListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        getDialog().getWindow().getAttributes().width=1800;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }

    public interface OrderCreateDialogListener{
        void createOrder(Order order, TablesSelectedFragment tablesSelectedFragment);
    }


    public void loadCategory(ArrayList<Category> categoryArrayList){
        categoryAndElementCreateOrderAdapter.clearCategory();
        categoryAndElementCreateOrderAdapter.setCategoryArrayList(categoryArrayList);
        categoryAndElementCreateOrderAdapter.notifyDataSetChanged();
    }

    public void loadElement(ArrayList<Element> elementArrayList){
        categoryAndElementCreateOrderAdapter.clearElement();
        categoryAndElementCreateOrderAdapter.setElementArrayList(elementArrayList);
        categoryAndElementCreateOrderAdapter.notifyDataSetChanged();

        categoryAndElementCreateOrderAdapter.setMergeList(categoryAndElementCreateOrderAdapter.getCategoryArrayList(), categoryAndElementCreateOrderAdapter.getElementArrayList());
    }

    public void loadSelectedElement(ArrayList<Element> elementArrayList){
        selectedElementOrderAdapter.setSelectedElementArrayList(elementArrayList);
        selectedElementOrderAdapter.notifyDataSetChanged();
    }

    public Double computeTotalPrice(ArrayList<Element> elementArrayList){
        double result = 0.0;

        for(Element element : elementArrayList){
            result = result + element.getPrice();
        }

        return Math.floor((result*100) / 100);

    }

    public SelectedElementOrderAdapter getSelectedElementOrderAdapter() {
        return selectedElementOrderAdapter;
    }

    public void setSelectedElementOrderAdapter(SelectedElementOrderAdapter selectedElementOrderAdapter) {
        this.selectedElementOrderAdapter = selectedElementOrderAdapter;
    }
}
