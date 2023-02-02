package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.Model.Element;

import java.util.ArrayList;

public class CategoryAndOrderAdapter extends RecyclerView.Adapter<CategoryAndOrderAdapter.CategoryAndElementHolder> {

    private ArrayList<Category> categoryArrayList;
    private ArrayList<Element> elementArrayList;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAndOrderAdapter.CategoryAndElementHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    // ***************************************************************************************


    public class CategoryAndElementHolder extends RecyclerView.ViewHolder{

        public CategoryAndElementHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
