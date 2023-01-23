package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.Model.Enumerations.Aliment_Type;
import com.ingsw.frontend.R;

public class CategoryCreateDialog extends AppCompatDialogFragment {
    private EditText editTextname;
    private Aliment_Type aliment;
    private Integer idMenu;

    private CategoryCreateDialogListener categoryCreateDialogListener;

    public CategoryCreateDialog(Aliment_Type aliment, Integer idMenu) {
        this.aliment = aliment;
        this.idMenu = idMenu;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.category_create_layout, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextname.getText().toString().isEmpty()){
                    String name = editTextname.getText().toString();

                    categoryCreateDialogListener.createElement(name,aliment,idMenu);
                    dialog.dismiss();
                }
            }
        });

        editTextname = view.findViewById(R.id.edit_category_name);

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            categoryCreateDialogListener = (CategoryCreateDialog.CategoryCreateDialogListener) context;
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

    public interface CategoryCreateDialogListener{
        void createElement(String name, Aliment_Type aliment,Integer idMenu);
    }
}
