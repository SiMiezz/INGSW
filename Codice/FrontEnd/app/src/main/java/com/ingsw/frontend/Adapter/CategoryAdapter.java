package com.ingsw.frontend.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    public ArrayList<String> arrayList;

    private final int NORMAL_LAYOUT = -1;
    private final int SELECTION_LAYOUT=-2;
    public static int currentLayout = -1;


    public CategoryAdapter(Context context,ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View normalList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_normal, parent,false);
        View selectionList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_selection, parent,false);

        if(getItemViewType(0) == -1)
            return new CategoryHolder(normalList);
        else
            return  new CategoryHolder(selectionList);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        holder.textView.setText(arrayList.get(position));
        if(holder.checkBox != null)
            holder.checkBox.setChecked(false);

    }

    @Override
    public int getItemViewType(int position){

        return currentLayout;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public ArrayList<String> getArrayList(){
        return arrayList;
    }



    public class CategoryHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView textView;
        private ImageButton removeButton;
        private ImageButton addButton;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_cardview);
            removeButton = itemView.findViewById(R.id.remove_category_button);
            addButton = itemView.findViewById(R.id.add_category_button);
            checkBox = itemView.findViewById(R.id.checkbox_category);

        }

    }
}
