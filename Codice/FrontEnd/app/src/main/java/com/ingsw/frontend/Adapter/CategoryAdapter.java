package com.ingsw.frontend.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Context context;

    private final int NORMAL_LAYOUT = -1;
    private final int SELECTION_LAYOUT=-2;


    public CategoryAdapter(Context context,ArrayList<String> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View normalList = LayoutInflater.from(context).inflate(R.layout.row_list_normal, parent,false);
        View selectionList = LayoutInflater.from(context).inflate(R.layout.row_list_selection, parent,false);

        return new CategoryHolder(selectionList);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryHolder holder, int position) {

        holder.textView.setText(arrayList.get(position));

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class CategoryHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageButton removeButton;
        private ImageButton addButton;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_cardview);
            removeButton = (ImageButton) itemView.findViewById(R.id.remove_category_button);
            addButton = (ImageButton) itemView.findViewById(R.id.add_category_button);

        }

    }
}
