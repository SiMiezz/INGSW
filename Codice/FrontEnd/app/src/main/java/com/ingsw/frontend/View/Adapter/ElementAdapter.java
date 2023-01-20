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
import com.ingsw.frontend.R;
import java.util.ArrayList;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ElementHolder> {

    public ArrayList<Element> arrayList;
    public ArrayList<Element> selectedItemsArrayList = new ArrayList<>();

    public static int currentLayout = -1;

    public ElementAdapter(Context context, ArrayList<Element> arrayList){
        this.arrayList = arrayList;
    }

    public void setArrayList(ArrayList<Element> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<Element> getArrayList(){
        return arrayList;
    }

    public void clearList(){
        arrayList.clear();
    }


    // ***************************************************************************************

    @NonNull
    @Override
    public ElementAdapter.ElementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View normalList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_nonclickable_element, parent,false);
        View selectionList = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_selection_element, parent,false);

        if(getItemViewType(0) == -1)
            return new ElementAdapter.ElementHolder(normalList);
        else
            return  new ElementAdapter.ElementHolder(selectionList);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementAdapter.ElementHolder holder, int position) {

//        holder.textView.setText(arrayList.get(position).getName());

        holder.name.setText(arrayList.get(position).getName().toUpperCase());
        holder.description.setText(arrayList.get(position).getDescription());
        holder.price.setText("€ " + String.valueOf(arrayList.get(position).getPrice()));
        if(arrayList.get(position).isPrePackaged())
            holder.prepackaged.setText("Yes");
        else
            holder.prepackaged.setText("No");

        holder.checkBox.setChecked(false);

        Element temp = arrayList.get(holder.getAdapterPosition());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp.setChecked(holder.checkBox.isChecked());

                if(temp.getChecked() == true){
                    selectedItemsArrayList.add(temp);
                }
                else if(temp.getChecked() == false && !(arrayList.contains(temp))){
                    selectedItemsArrayList.remove(temp);
                }
            }
        });


        if(temp.getChecked() == true)
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position){

        return currentLayout;
    }

    public ArrayList<Element> getSelectedItemsArrayList() {
        return selectedItemsArrayList;
    }


    // ***************************************************************************************

    public class ElementHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView name;
        private TextView description;
        private TextView price;
        private TextView prepackaged;

        public ElementHolder(@NonNull View itemView) {
            super(itemView);
//            textView = itemView.findViewById(R.id.text_cardview);
            checkBox = itemView.findViewById(R.id.checkbox_category);
            name = itemView.findViewById(R.id.element_name_cardview);
            description = itemView.findViewById(R.id.element_description_cardview);
            price = itemView.findViewById(R.id.element_price_cardview);
            prepackaged = itemView.findViewById(R.id.element_prepackaged_cardview);

        }

    }
}
