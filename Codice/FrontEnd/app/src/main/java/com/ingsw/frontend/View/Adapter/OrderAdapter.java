package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.Model.Order;
import com.ingsw.frontend.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    private ArrayList<Order> orderArrayList = new ArrayList<>();
    public ArrayList<Order> selectedItemsArrayList = new ArrayList<>();

    public int currentLayout = -1;

    public OrderAdapter(Context context, ArrayList<Order> orderArrayList){
        this.orderArrayList = orderArrayList;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View normalList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order, parent,false);
        View selectionList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_selection, parent,false);

        if(getItemViewType(0) == -1)
            return new OrderAdapter.OrderHolder(normalList);
        else
            return  new OrderAdapter.OrderHolder(selectionList);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderHolder holder, int position) {

        Order temp = orderArrayList.get(holder.getAdapterPosition());

        holder.checkBox.setChecked(false);

        String elements = "";

        for(Element element : temp.getElements())
            elements = elements + element.getName().toUpperCase() + ", ";

        if(elements.length() > 0)
            elements = elements.substring(0, elements.length() - 2);

        holder.orderElement.setText(elements);

        holder.orderPrice.setText("€ " + String.valueOf(temp.getPrice()));

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp.setChecked(holder.checkBox.isChecked());

                if(temp.getChecked()){
                    selectedItemsArrayList.add(temp);
                }
                else{
                    selectedItemsArrayList.remove(temp);
                }
            }
        });

        holder.checkBox.setChecked(temp.getChecked());
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

    public void clearList(){
        orderArrayList.clear();
    }

    public ArrayList<Order> getSelectedItemsArrayList() {
        return selectedItemsArrayList;
    }

    public void setSelectedItemsArrayList(ArrayList<Order> selectedItemsArrayList) {
        this.selectedItemsArrayList = selectedItemsArrayList;
    }

    // ***************************************************************************************
    // ***************************************************************************************
    // ***************************************************************************************

    public class OrderHolder extends RecyclerView.ViewHolder{

        TextView orderElement;
        TextView orderPrice;
        CheckBox checkBox;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            orderElement = itemView.findViewById(R.id.order_elements);
            orderPrice = itemView.findViewById(R.id.order_price);
            checkBox = itemView.findViewById(R.id.order_checkbox);
        }
    }


}
