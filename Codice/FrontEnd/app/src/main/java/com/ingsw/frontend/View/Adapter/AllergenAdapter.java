package com.ingsw.frontend.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.R;

import java.util.ArrayList;
import java.util.List;

public class AllergenAdapter extends ArrayAdapter<Allergen> {

    private ArrayList<Allergen> allergenArrayList;
    private ArrayList<Allergen> selectedAllergenArrayList;
    private Context context;



    public AllergenAdapter(@NonNull Context context, int resource, @NonNull List<Allergen> allergenArrayList) {
        super(context, resource, allergenArrayList);
        this.context = context;
        this.allergenArrayList = (ArrayList<Allergen>) allergenArrayList;
        this.selectedAllergenArrayList = new ArrayList<>();
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent){

        final AllergenHolder holder;

        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(context);
            convertView = layoutInflator.inflate(R.layout.row_allergen_selection, null);
            holder = new AllergenHolder();
            holder.allergenText = (TextView) convertView.findViewById(R.id.allergen_text);
            holder.allergenCheckBox = (CheckBox) convertView.findViewById(R.id.allergen_checkbox);
            convertView.setTag(holder);
        } else {
            holder = (AllergenHolder) convertView.getTag();
        }

        Allergen currentAllergen = allergenArrayList.get(position);

        holder.allergenText.setText(currentAllergen.getName().toUpperCase());

        if(holder.allergenCheckBox.isChecked())
            if( !(selectedAllergenArrayList.contains(currentAllergen)))
                selectedAllergenArrayList.add(currentAllergen);

        if( !(holder.allergenCheckBox.isChecked()))
            if(selectedAllergenArrayList.contains(currentAllergen))
                selectedAllergenArrayList.remove(currentAllergen);

        return convertView;
    }

    public ArrayList<Allergen> getAllergenArrayList() {
        return allergenArrayList;
    }

    public void setAllergenArrayList(ArrayList<Allergen> allergenArrayList) {
        this.allergenArrayList = allergenArrayList;
    }

    public ArrayList<Allergen> getSelectedAllergenArrayList() {
        return selectedAllergenArrayList;
    }

    public void setSelectedAllergenArrayList(ArrayList<Allergen> selectedAllergenArrayList) {
        this.selectedAllergenArrayList = selectedAllergenArrayList;
    }

    private class AllergenHolder{
        private TextView allergenText;
        private CheckBox allergenCheckBox;
    }

}
