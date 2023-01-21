package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.R;

public class UserDialog extends AppCompatDialogFragment {
    private EditText editTextname;
    private EditText editTextsurname;
    private EditText editTextemail;
    private User_Type job;
    private String nameRestaurant;

    private UserDialogListener userDialogListener;

    public UserDialog(User_Type job, String nameRestaurant) {
        this.job = job;
        this.nameRestaurant = nameRestaurant;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.user_layout, null);

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
                        String surname = editTextsurname.getText().toString();
                        String email = editTextemail.getText().toString();

                        userDialogListener.createUser(name,surname,email,job,nameRestaurant);
                    }
                });

        editTextname = view.findViewById(R.id.edit_user_name);
        editTextsurname = view.findViewById(R.id.edit_user_surname);
        editTextemail = view.findViewById(R.id.edit_user_email);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            userDialogListener = (UserDialogListener) context;
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

    public interface UserDialogListener{
        void createUser(String name,String surname,String email,User_Type job,String restaurant);
    }
}
