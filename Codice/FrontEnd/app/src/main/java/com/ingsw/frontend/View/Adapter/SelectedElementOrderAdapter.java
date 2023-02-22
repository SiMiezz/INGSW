package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SelectedElementOrderAdapter extends RecyclerView.Adapter<SelectedElementOrderAdapter.SelectedElementOrderHolder> {

    private ArrayList<Element> selectedElementArrayList;
    private ArrayList<Element> groupedSelectedElementArrayList = new ArrayList<>();
    private ArrayList<Element> toRemoveSelectedElementArrayList = new ArrayList<>();

    public static int currentLayout = -1;

    public SelectedElementOrderAdapter(Context context, ArrayList<Element> selectedElementArrayList){
        this.selectedElementArrayList = selectedElementArrayList;
    }

    public ArrayList<Element> getSelectedElementArrayList() {
        return selectedElementArrayList;
    }

    public void setSelectedElementArrayList(ArrayList<Element> selectedElementArrayList) {
        this.selectedElementArrayList = selectedElementArrayList;
    }

    public static int getCurrentLayout() {
        return currentLayout;
    }

    public static void setCurrentLayout(int currentLayout) {
        SelectedElementOrderAdapter.currentLayout = currentLayout;
    }

    public ArrayList<Element> getGroupedSelectedElementArrayList() {
        return groupedSelectedElementArrayList;
    }

    public void setGroupedSelectedElementArrayList(ArrayList<Element> groupedSelectedElementArrayList) {
        this.groupedSelectedElementArrayList = groupedSelectedElementArrayList;
    }

    public ArrayList<Element> getToRemoveSelectedElementArrayList() {
        return toRemoveSelectedElementArrayList;
    }

    public void setToRemoveSelectedElementArrayList(ArrayList<Element> toRemoveSelectedElementArrayList) {
        this.toRemoveSelectedElementArrayList = toRemoveSelectedElementArrayList;
    }

    @NonNull
    @Override
    public SelectedElementOrderAdapter.SelectedElementOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View normalList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_create_order_selected_element_nonclickable, parent,false);
        View selectionList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_selection, parent,false);

        if(getItemViewType(0) == -1){
            return new SelectedElementOrderAdapter.SelectedElementOrderHolder(normalList);
        }
        else
            return  new SelectedElementOrderAdapter.SelectedElementOrderHolder(selectionList);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedElementOrderAdapter.SelectedElementOrderHolder holder, int position) {

        if(selectedElementArrayList.size() > 0){
            Collections.sort(selectedElementArrayList, new Comparator<Element>() {
                @Override
                public int compare(Element element1, Element element2) {
                    return element1.getName().compareTo(element2.getName());
                }
            });
        }

        for(Element element : selectedElementArrayList){
            if(!(groupedSelectedElementArrayList.contains(element)))
                groupedSelectedElementArrayList.add(element);
        }

        for(Element element : groupedSelectedElementArrayList){
            element.setQuantityOrdered(Collections.frequency(selectedElementArrayList, element));
        }

        if(groupedSelectedElementArrayList.size() > 0){
            Collections.sort(groupedSelectedElementArrayList, new Comparator<Element>() {
                @Override
                public int compare(Element element1, Element element2) {
                    return element1.getName().compareTo(element2.getName());
                }
            });
        }

        if(currentLayout == -1){
            try{
                holder.textView.setText(groupedSelectedElementArrayList.get(holder.getAdapterPosition()).getName().toUpperCase());
                holder.quantity.setText("x "+groupedSelectedElementArrayList.get(holder.getAdapterPosition()).getQuantityOrdered());

            } catch (Exception e){
                System.out.println(e);
            }

        }
        else if(currentLayout == -2){

            holder.textView.setText(selectedElementArrayList.get(holder.getAdapterPosition()).getName().toUpperCase());
            holder.checkBox.setChecked(false);

            Element temp = selectedElementArrayList.get(holder.getAdapterPosition());

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    temp.setChecked(holder.checkBox.isChecked());

                    if(temp.getChecked())
                        toRemoveSelectedElementArrayList.add(temp);
                    else {
                        toRemoveSelectedElementArrayList.remove(temp);
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(currentLayout == -1){
            if(groupedSelectedElementArrayList.size() > 0){
                return groupedSelectedElementArrayList.size();
            }
            else{
                return selectedElementArrayList.size();
            }
        }
        else
            return selectedElementArrayList.size();
    }

    @Override
    public int getItemViewType(int position){
        return currentLayout;
    }


    public class SelectedElementOrderHolder extends RecyclerView.ViewHolder{

        private CheckBox checkBox;
        private TextView textView;
        private TextView quantity;
        private CardView cardView;

        public SelectedElementOrderHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.row_clickable_item);
            textView = itemView.findViewById(R.id.text_cardview);
            checkBox = itemView.findViewById(R.id.checkbox_category);
            quantity = itemView.findViewById(R.id.text_quantity_element);
        }
    }
}
