package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Model.Menu;
import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.Presenter.MenuCategoriesPresenter;
import com.ingsw.frontend.Presenter.MenuElementsPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.CategoryAndOrderAdapter;
import com.ingsw.frontend.View.Fragment.TablesSelectedFragment;

import java.util.ArrayList;

public class OrderCreateDialog extends AppCompatDialogFragment {

    private OrderCreateDialogListener orderCreateDialogListener;
    private TablesSelectedFragment tablesSelectedFragment;

    private RecyclerView allElementsRecyclerView;
    private RecyclerView selectedElementsRecyclerView;
    private MenuCategoriesPresenter menuCategoriesPresenter = new MenuCategoriesPresenter(this);
    private MenuElementsPresenter menuElementsPresenter = new MenuElementsPresenter(this);

    private CategoryAndOrderAdapter categoryAndOrderAdapter;
    private ArrayList<Category> categoryArrayList;
    private ArrayList<Element> elementArrayList;

    private Intent intent;
    private Menu menu;

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
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", null)
                .show();

        allElementsRecyclerView = view.findViewById(R.id.create_order_all_elements);
        selectedElementsRecyclerView = view.findViewById(R.id.create_order_selected_elements);

        categoryArrayList = new ArrayList<>();
        elementArrayList = new ArrayList<>();

        categoryAndOrderAdapter = new CategoryAndOrderAdapter(getContext(), categoryArrayList, elementArrayList);

        intent = getActivity().getIntent();

        menu = (Menu) intent.getSerializableExtra("menu");

        //presenters + andrebbe fatta la divisione food/drink con un tab layout

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        allElementsRecyclerView.setLayoutManager(linearLayoutManager);
        allElementsRecyclerView.setAdapter(categoryAndOrderAdapter);



        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = new Order();

                orderCreateDialogListener.createOrder(order, tablesSelectedFragment);
                dialog.dismiss();
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
        getDialog().getWindow().getAttributes().width=800;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }

    public interface OrderCreateDialogListener{
        void createOrder(Order order, TablesSelectedFragment tablesSelectedFragment);
    }
}
