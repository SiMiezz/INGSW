package com.example.natour21.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.natour21.Entity.Utente;
import com.example.natour21.Presenter.ProfiloPresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.View.Other.HomePageActivity;
import com.example.natour21.View.Other.TracciatiPersonaliActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class ProfiloFragment extends Fragment {

    private ProfiloPresenter mProfiloPresenter;
    private final String TAG = "ProfiloFragment";

    private ImageView fotoProfilo, editFotoProfiloImageButton;
    private TextView username, nome, cognome, email , cancellaAccount;
    private Button buttonTracciatiPersonali;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private final int PICK_IMAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profilo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fotoProfilo = (ImageView) getView().findViewById(R.id.imageViewFotoProfiloAcProfilo);
        editFotoProfiloImageButton = (ImageButton) getView().findViewById(R.id.imageViewEditFotoProfilo);
        username = (TextView) getView().findViewById(R.id.textViewUsernameEffettivoAcProfilo);
        nome = (TextView) getView().findViewById(R.id.textViewNomeEffettivoAcProfilo);
        cognome = (TextView) getView().findViewById(R.id.textViewCognomeEffettivoAcProfilo);
        email = (TextView) getView().findViewById(R.id.textViewEmailEffettivaAcProfilo);
        buttonTracciatiPersonali = (Button) getView().findViewById(R.id.buttonTracciatiPersonaliAcProfilo);
        cancellaAccount = (TextView) getView().findViewById(R.id.textViewCancellaAccount);

        mProfiloPresenter = new ProfiloPresenter(this);

        setProfiloFragment();

        fotoProfilo.setOnClickListener(v -> {
            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

            startActivityForResult(chooserIntent, PICK_IMAGE);
        });

        editFotoProfiloImageButton.setOnClickListener(v -> {
            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

            startActivityForResult(chooserIntent, PICK_IMAGE);
        });

        buttonTracciatiPersonali.setOnClickListener(v -> {
            Log.i(TAG, "Click tracciati personali.");
            Intent newIntent = new Intent(getActivity().getApplicationContext(), TracciatiPersonaliActivity.class);
            startActivity(newIntent);
        });

        cancellaAccount.setOnClickListener(v -> {
                Log.i(TAG, "onStart Called.");
                new MaterialAlertDialogBuilder(getActivity(), R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Cancella account")
                .setMessage("Sei sicuro di voler cancellare l'account?")
                .setPositiveButton("Cancella", (dialogInterface, i) -> mProfiloPresenter.deleteAccount())
                .setNegativeButton("Annulla", (dialogInterface, i) -> {})
                .show();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == HomePageActivity.RESULT_OK) {
            popupChangeImage(data);
        }
    }

    public void popupChangeImage(Intent data) {
        Log.i(TAG, "Opening: popupChangeImage.");
        dialogBuilder = new AlertDialog.Builder(getContext());
        final View popupView = getLayoutInflater().inflate(R.layout.activity_popup_change_photo, null);
        ImageView fotoProfiloPopup = (ImageView) popupView.findViewById(R.id.imageViewPopupCambiaFoto);
        Button confermaButton = (Button) popupView.findViewById(R.id.buttonConfermaFotoPopup);
        Button annullaButton = (Button) popupView.findViewById(R.id.buttonAnnullaFotoPopup);

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        Glide.with(ProfiloFragment.this)
                .load(data.getData())
                .circleCrop()
                .dontAnimate()
                .into(fotoProfiloPopup);

        confermaButton.setOnClickListener(v -> {
            Log.i(TAG, "Click Conferma new image.");
            mProfiloPresenter.uploadProfile(data.getData());
            dialog.dismiss();
        });

        annullaButton.setOnClickListener(v -> {
            Log.i(TAG, "Click Annulla new image.");
            dialog.dismiss();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume Called.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart Called.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause Called.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop Called.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy Called.");
    }

    public void setProfiloFragment(){
        Log.i(TAG, "Setting profile framgent image.");
        Utente user = LocalUser.getLocalUser(this.getActivity());
        if(!user.getPhotolnk().equals(""))
            mProfiloPresenter.setPhotoProfilePage(fotoProfilo,user.getPhotolnk());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        nome.setText(user.getNome());
        cognome.setText(user.getCognome());
    }

    @NonNull
    @Override
    public String toString() {
        return "ProfiloFragment";
    }
}