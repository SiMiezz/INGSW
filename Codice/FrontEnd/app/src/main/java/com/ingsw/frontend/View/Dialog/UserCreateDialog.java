package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.User;
import com.ingsw.frontend.R;
import com.ingsw.frontend.View.Activity.HomeActivity;
import com.ingsw.frontend.View.Fragment.MembersAdminFragment;
import com.ingsw.frontend.View.Fragment.MembersChefsFragment;
import com.ingsw.frontend.View.Fragment.MembersSupervisorsFragment;
import com.ingsw.frontend.View.Fragment.MembersWaitersFragment;

public class UserCreateDialog extends AppCompatDialogFragment {
    private EditText editTextname;
    private EditText editTextsurname;
    private EditText editTextemail;
    private UserCreateDialogListener userCreateDialogListener;

    private MembersSupervisorsFragment membersSupervisorsFragment;
    private MembersWaitersFragment membersWaitersFragment;
    private MembersChefsFragment membersChefsFragment;

    public UserCreateDialog(MembersSupervisorsFragment membersSupervisorsFragment) {
        this.membersSupervisorsFragment = membersSupervisorsFragment;
        membersChefsFragment = null;
        membersWaitersFragment = null;
    }

    public UserCreateDialog(MembersWaitersFragment membersWaitersFragment) {
        this.membersWaitersFragment = membersWaitersFragment;
        membersChefsFragment = null;
        membersSupervisorsFragment = null;
    }

    public UserCreateDialog(MembersChefsFragment membersChefsFragment) {
        this.membersChefsFragment = membersChefsFragment;
        membersSupervisorsFragment = null;
        membersWaitersFragment = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.user_create_layout, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextname.getText().toString().isEmpty() || editTextemail.getText().toString().isEmpty())){
                    String name = editTextname.getText().toString();
                    String surname = editTextsurname.getText().toString();
                    String email = editTextemail.getText().toString();

                    userCreateDialogListener.createUser(email,User.getDefaultPwd(),name,surname,membersSupervisorsFragment,membersWaitersFragment,membersChefsFragment);
                    dialog.dismiss();
                }
            }
        });

        editTextname = view.findViewById(R.id.edit_user_name);
        editTextsurname = view.findViewById(R.id.edit_user_surname);
        editTextemail = view.findViewById(R.id.edit_user_email);

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            userCreateDialogListener = (UserCreateDialogListener) context;
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

    public interface UserCreateDialogListener {
        void createUser(String email,String pwd,String name,String surname, MembersSupervisorsFragment membersSupervisorsFragment, MembersWaitersFragment membersWaitersFragment, MembersChefsFragment membersChefsFragment);
    }
}
