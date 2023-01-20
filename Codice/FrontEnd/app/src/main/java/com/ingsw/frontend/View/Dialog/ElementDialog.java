package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.R;

public class ElementDialog extends AppCompatDialogFragment {
    private EditText editTextname;
    private EditText editTextprice;
    private EditText editTextdescription;
    private CheckBox checkBoxprepackaged;
    private Integer idCategory;

    private ElementDialogListener elementDialogListener;

    public ElementDialog(Integer idCategory) {
        this.idCategory = idCategory;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.element_layout, null);

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editTextname.getText().toString();
                        String description = editTextdescription.getText().toString();
                        Double price = Double.parseDouble(editTextprice.getText().toString());
                        Boolean prepackaged = checkBoxprepackaged.isChecked();

                        elementDialogListener.createElement(name,description,price,prepackaged,idCategory);
                    }
                });

        editTextname = view.findViewById(R.id.edit_name);
        editTextprice = view.findViewById(R.id.edit_price);
        editTextdescription = view.findViewById(R.id.edit_description);
        checkBoxprepackaged = view.findViewById(R.id.checkbox_prepackaged);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            elementDialogListener = (ElementDialogListener) context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getAttributes().width=1000;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }

    public interface ElementDialogListener{
        void createElement(String name,String description,Double price,Boolean prepackaged,Integer idCategory);
    }
}
