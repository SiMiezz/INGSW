package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.R;

public class LoginFailDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.login_fail_layout, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("OK",null)
                .show();

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getAttributes().width=800;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }
}
