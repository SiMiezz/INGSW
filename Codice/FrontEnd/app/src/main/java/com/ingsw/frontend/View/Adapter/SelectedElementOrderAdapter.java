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

public class SelectedElementOrderAdapter extends RecyclerView.Adapter<SelectedElementOrderAdapter.SelectedElementOrderHolder> {

    private ArrayList<Element> selectedElementArrayList;
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

    @NonNull
    @Override
    public SelectedElementOrderAdapter.SelectedElementOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View normalList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_nonclickable, parent,false);
        View selectionList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_selection, parent,false);

        if(getItemViewType(0) == -1)
            return new SelectedElementOrderAdapter.SelectedElementOrderHolder(normalList);
        else
            return  new SelectedElementOrderAdapter.SelectedElementOrderHolder(selectionList);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedElementOrderAdapter.SelectedElementOrderHolder holder, int position) {
        holder.textView.setText(selectedElementArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return selectedElementArrayList.size();
    }

    @Override
    public int getItemViewType(int position){

        return currentLayout;
    }


    public class SelectedElementOrderHolder extends RecyclerView.ViewHolder{

        private CheckBox checkBox;
        private TextView textView;
        private CardView cardView;

        public SelectedElementOrderHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.row_clickable_item);
            textView = itemView.findViewById(R.id.text_cardview);
            checkBox = itemView.findViewById(R.id.checkbox_category);

        }
    }
}
