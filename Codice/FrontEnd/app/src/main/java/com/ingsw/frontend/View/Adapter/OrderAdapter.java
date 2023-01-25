package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    private ArrayList<Order> orderArrayList = new ArrayList<>();

    public int currentLayout = -1;

    public OrderAdapter(Context context, ArrayList<Order> orderArrayList){
        this.orderArrayList = orderArrayList;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderAdapter.OrderHolder((LayoutInflater
                                            .from(parent.getContext())
                                            .inflate(R.layout.row_order, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    @Override
    public int getItemViewType(int position){
        return currentLayout;
    }

    public ArrayList<Order> getOrderArrayList() {
        return orderArrayList;
    }

    public void setOrderArrayList(ArrayList<Order> orderArrayList) {
        this.orderArrayList = orderArrayList;
    }

    public int getCurrentLayout() {
        return currentLayout;
    }

    public void setCurrentLayout(int currentLayout) {
        this.currentLayout = currentLayout;
    }


    // ***************************************************************************************
    // ***************************************************************************************
    // ***************************************************************************************

    public class OrderHolder extends RecyclerView.ViewHolder{

        TextView orderElement;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
//            orderElement = itemView.findViewById(R.id.);
        }
    }


}
