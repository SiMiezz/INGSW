package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Element;
import com.ingsw.frontend.R;

import java.util.ArrayList;

public class CategoryAndOrderAdapter extends RecyclerView.Adapter<CategoryAndOrderAdapter.CategoryAndElementHolder> {

    private ArrayList<Category> categoryArrayList;
    private ArrayList<Element> elementArrayList;
    private ArrayList mergeList;

    public CategoryAndOrderAdapter(Context context, ArrayList<Category> categoryArrayList, ArrayList<Element> elementArrayList){
        this.categoryArrayList = categoryArrayList;
        this.elementArrayList = elementArrayList;
    }

    public ArrayList<Category> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }

    public ArrayList<Element> getElementArrayList() {
        return elementArrayList;
    }

    public void setElementArrayList(ArrayList<Element> elementArrayList) {
        this.elementArrayList = elementArrayList;
    }

    @NonNull
    @Override
    public CategoryAndOrderAdapter.CategoryAndElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_create_order_category, parent, false);
        View elementList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_create_order_element, parent, false);

        if(viewType == 0)
            return new CategoryAndElementHolder(categoryList);
        else
            return new CategoryAndElementHolder(elementList);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAndOrderAdapter.CategoryAndElementHolder holder, int position) {

        if(mergeList.get(position) instanceof Category){
            // ...
        }
        else if(mergeList.get(position) instanceof Element){
            // ...
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position){
        if(mergeList.get(position) instanceof Category)
            return 0;
        else if(mergeList.get(position) instanceof Element)
            return 1;
        else
            return -1;
    }


    // ***************************************************************************************


    public class CategoryAndElementHolder extends RecyclerView.ViewHolder{

        public CategoryAndElementHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
