package com.ingsw.frontend.View.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ingsw.frontend.R;

public class BillDialog extends AppCompatDialogFragment {

    private EditText editTextBill;
    private Double bill;

    public BillDialog(Double bill) {
        this.bill = bill;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.bill_layout, null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("ok",null)
                .show();

        editTextBill = view.findViewById(R.id.edit_bill);
        editTextBill.setText(bill.toString() + "€");

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getAttributes().width=600;
        getDialog().getWindow().setAttributes(
                getDialog().getWindow().getAttributes());
    }
}
