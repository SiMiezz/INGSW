package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.Model.Allergen;
import com.ingsw.frontend.Presenter.AllergenPresenter;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Adapter.AllergenAdapter;
import com.ingsw.frontend.View.Fragment.MenuElementsFragment;

import java.util.ArrayList;

public class ElementCreateDialog extends AppCompatDialogFragment {
    private EditText editTextname;
    private EditText editTextTranslateName;
    private EditText editTextdescription;
    private EditText editTextTranslateDescription;
    private EditText editTextprice;
    private CheckBox checkBoxprepackaged;

    private TextView viewTranslateName;
    private TextView viewTranslateDescription;
    private Spinner spinner;

    private ArrayList<Allergen> allergenArrayList;

    private ElementCreateDialogListener elementCreateDialogListener;
    private MenuElementsFragment menuElementsFragment;
    private AllergenPresenter allergenPresenter = new AllergenPresenter(this);
    private AllergenAdapter allergenAdapter;

    public ElementCreateDialog(MenuElementsFragment menuElementsFragment) {
        this.menuElementsFragment = menuElementsFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.element_create_layout, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextname.getText().toString().isEmpty() || editTextprice.getText().toString().isEmpty())){
                    String name = editTextname.getText().toString();
                    String translateName = editTextTranslateName.getText().toString();
                    String description = editTextdescription.getText().toString();
                    String translateDescription = editTextTranslateDescription.getText().toString();
                    Boolean prepackaged = checkBoxprepackaged.isChecked();
                    Double price = Double.parseDouble(editTextprice.getText().toString());

                    elementCreateDialogListener.createElement(name,translateName,description,translateDescription,price,prepackaged,menuElementsFragment);
                    dialog.dismiss();
                }
            }
        });

        allergenArrayList = new ArrayList<>();
        editTextname = view.findViewById(R.id.edit_element_name);
        editTextTranslateName = view.findViewById(R.id.edit_element_translatename);
        editTextdescription = view.findViewById(R.id.edit_element_description);
        editTextTranslateDescription = view.findViewById(R.id.edit_element_translatedescription);
        editTextprice = view.findViewById(R.id.edit_element_price);
        checkBoxprepackaged = view.findViewById(R.id.checkbox_prepackaged);
        spinner = view.findViewById(R.id.allergen_spinner);

        viewTranslateName = view.findViewById(R.id.view_translatename);
        viewTranslateDescription = view.findViewById(R.id.view_translatedescription);

        if(!menuElementsFragment.getRestaurant().isTouristic()){
            editTextTranslateDescription.setEnabled(false);
            editTextTranslateName.setEnabled(false);
        }

        allergenPresenter.getAllAllergens();
        allergenAdapter = new AllergenAdapter(getContext(), 0, allergenArrayList);
        spinner.setAdapter(allergenAdapter);

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            elementCreateDialogListener = (ElementCreateDialogListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getAttributes().width=650;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }

    public interface ElementCreateDialogListener {
        void createElement(String name,String translateName,String description,String translateDescription,Double price,Boolean prepackaged,MenuElementsFragment menuElementsFragment);
    }



    public void loadAllergens(ArrayList<Allergen> allergens) {
        for(Allergen allergen : allergens)
            allergenArrayList.add(allergen);
    }
}
