package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.R;

public class ErrorDialog extends AppCompatDialogFragment {

    private TextView textView;
    private String message;

    public ErrorDialog(String message){
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.error_layout, null);

        Button errorButton = view.findViewById(R.id.error_button);
        textView = view.findViewById(R.id.error_message);

        textView.setText(message);


        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .show();

        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getAttributes().width=775;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
