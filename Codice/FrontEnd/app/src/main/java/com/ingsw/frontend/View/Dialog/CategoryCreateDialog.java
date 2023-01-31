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
import com.ingsw.frontend.View.Fragment.MenuCategoriesDrinkFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFoodFragment;
import com.ingsw.frontend.View.Fragment.MenuCategoriesFragment;
import com.ingsw.frontend.View.Fragment.MenuElementsFragment;

public class CategoryCreateDialog extends AppCompatDialogFragment {
    private EditText editTextname;
    private Aliment_Type aliment;
    private Integer idMenu;

    private CategoryCreateDialogListener categoryCreateDialogListener;
    private MenuCategoriesFoodFragment menuCategoriesFoodFragment;
    private MenuCategoriesDrinkFragment menuCategoriesDrinkFragment;

    public CategoryCreateDialog(MenuCategoriesFoodFragment menuCategoriesFoodFragment) {
        this.menuCategoriesFoodFragment = menuCategoriesFoodFragment;
        menuCategoriesDrinkFragment = null;
    }

    public CategoryCreateDialog(MenuCategoriesDrinkFragment menuCategoriesDrinkFragment) {
        this.menuCategoriesDrinkFragment = menuCategoriesDrinkFragment;
        menuCategoriesFoodFragment = null;
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

                    categoryCreateDialogListener.createCategory(name,menuCategoriesFoodFragment,menuCategoriesDrinkFragment);
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
        getDialog().getWindow().getAttributes().width=500;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }

    public interface CategoryCreateDialogListener{
        void createCategory(String name,MenuCategoriesFoodFragment menuCategoriesFoodFragment,MenuCategoriesDrinkFragment menuCategoriesDrinkFragment);
    }
}
