package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.R;

public class ElementCreateDialog extends AppCompatDialogFragment {
    private EditText editTextname;
    private EditText editTextprice;
    private EditText editTextdescription;
    private CheckBox checkBoxprepackaged;
    private Integer idCategory;

    private ElementDialogListener elementDialogListener;

    public ElementCreateDialog(Integer idCategory) {
        this.idCategory = idCategory;
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
                    String description = editTextdescription.getText().toString();
                    Boolean prepackaged = checkBoxprepackaged.isChecked();
                    Double price = Double.parseDouble(editTextprice.getText().toString());

                    elementDialogListener.createElement(name,description,price,prepackaged,idCategory);
                    dialog.dismiss();
                }
            }
        });

        editTextname = view.findViewById(R.id.edit_name);
        editTextprice = view.findViewById(R.id.edit_price);
        editTextdescription = view.findViewById(R.id.edit_description);
        checkBoxprepackaged = view.findViewById(R.id.checkbox_prepackaged);

        return dialog;
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
