package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ingsw.frontend.Model.Category;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Fragment.MenuCategoriesDrinkFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFoodFragment;
import com.ingsw.frontend.View.Fragment.MenuElementsFragment;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    public ArrayList<Category> arrayList;
    private ArrayList<Category> selectedItemsArrayList = new ArrayList<>();

    public static int currentLayout = -1;
    public static Integer currentId;

    private MenuElementsFragment menuElementsFragment;
    private MenuCategoriesFoodFragment menuCategoriesFoodFragment;
    private MenuCategoriesDrinkFragment menuCategoriesDrinkFragment;


    public CategoryAdapter(Context context, ArrayList<Category> arrayList, MenuElementsFragment menuElementsFragment, MenuCategoriesFoodFragment menuCategoriesFoodFragment, MenuCategoriesDrinkFragment menuCategoriesDrinkFragment){
        this.arrayList = arrayList;
        this.menuElementsFragment = menuElementsFragment;
        this.menuCategoriesFoodFragment = menuCategoriesFoodFragment;
        this.menuCategoriesDrinkFragment = menuCategoriesDrinkFragment;
    }

    public ArrayList<Category> getArrayList(){
        return arrayList;
    }

    public void setArrayList(ArrayList<Category> arrayList) {
        this.arrayList = arrayList;
    }

    public void clearList(){
        arrayList.clear();
    }

    public void setSelectedItemsArrayList(ArrayList<Category> selectedItemsArrayList) {
        this.selectedItemsArrayList = selectedItemsArrayList;
    }

    public ArrayList<Category> getSelectedItemsArrayList() {
        return selectedItemsArrayList;
    }



 // ***************************************************************************************


    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        currentId = -1;

        View normalList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_clickable, parent,false);
        View selectionList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_selection, parent,false);
        View sortList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_sort, parent,false);

        if(getItemViewType(0) == -1)
            return new CategoryHolder(normalList);
        else if(getItemViewType(0) == -2)
            return new CategoryHolder(selectionList);
        else
            return new CategoryHolder(sortList);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        if(holder.cardView != null){
            if(arrayList.get(holder.getAdapterPosition()).isClicked())
                holder.cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c5c5c5")));
            else
                holder.cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        }
        
        holder.textView.setText(arrayList.get(position).getName().toUpperCase());
        holder.checkBox.setChecked(false);

        Category temp = arrayList.get(holder.getAdapterPosition());

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

        if(holder.cardView != null)
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentId = temp.getId();
                    menuElementsFragment.setCategoryId(temp.getId());
                    menuElementsFragment.getElementFromClick(temp.getId());

                    if(menuCategoriesFoodFragment != null)
                        menuCategoriesFoodFragment.clickEffect(arrayList.get(holder.getAdapterPosition()));
                    else if(menuCategoriesDrinkFragment != null)
                        menuCategoriesDrinkFragment.clickEffect(arrayList.get(holder.getAdapterPosition()));


            }
        });

    }

    @Override
    public int getItemViewType(int position){

        return currentLayout;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




    // ***************************************************************************************



    public class CategoryHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView textView;
        private CardView cardView;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.row_clickable_item);
            textView = itemView.findViewById(R.id.text_cardview);
            checkBox = itemView.findViewById(R.id.checkbox_category);

        }

    }
}
