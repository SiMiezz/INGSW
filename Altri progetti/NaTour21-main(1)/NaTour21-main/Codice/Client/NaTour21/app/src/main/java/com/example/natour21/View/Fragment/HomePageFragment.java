package com.example.natour21.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Presenter.HomePagePresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.ItinerariAdapter;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.View.Other.PubblicaItinerarioActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Map;

public class HomePageFragment extends Fragment {

    private HomePagePresenter mHomePagePresenter;
    private final String TAG = "HomePageFragment";

    private EditText cercaItinerarioEditText;
    private TextView benvenutoUtente;
    private ImageButton filtriButton;
    private Button aggiungiItinerarioButton;
    private RecyclerView recyclerView;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pagina_iniziale, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHomePagePresenter = new HomePagePresenter(this);

        benvenutoUtente = (TextView) getView().findViewById(R.id.textViewBenvenutoHomePage);
        cercaItinerarioEditText = (EditText) getView().findViewById(R.id.EditTextCercaInHomePage);
        filtriButton = (ImageButton) getView().findViewById(R.id.buttonFiltriHomePage);
        aggiungiItinerarioButton = (Button) getView().findViewById(R.id.buttonAggiungiPercorsoHomePageFragment);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewPaginaIniziale);

        ItinerariAdapter itinerarioAdapter = new ItinerariAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(itinerarioAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        setBenvenutoUtente();

        mHomePagePresenter.getItinerari();

        cercaItinerarioEditText.setOnKeyListener((v, keyCode, event) -> {
            Log.i(TAG,"Click: search.");
            if(keyCode == KeyEvent.KEYCODE_ENTER){
                String search = cercaItinerarioEditText.getText().toString();
                if(search.equals(""))
                    mHomePagePresenter.getItinerari();
                else
                    mHomePagePresenter.getItinerariByName(search);
                View view1 = getActivity().getCurrentFocus();
                if(view1 !=null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
                }
            }else if(keyCode == KeyEvent.KEYCODE_DEL)
                cercaItinerarioEditText.setText("");
            return true;
        });

        aggiungiItinerarioButton.setOnClickListener(v -> {
            if (!LocalUser.isGuest(getActivity())){
                Intent newIntent = new Intent(getActivity(), PubblicaItinerarioActivity.class);
                startActivity(newIntent);
            }
        });

        filtriButton.setOnClickListener(v -> popupFiltri());
    }

    private void popupFiltri(){
        Log.i(TAG,"Click: open filter.");
        EditText puntoInizio, puntoFine, lunghezza, areaGeograficaEditText;
        TextView textViewTitleFilter, difficultyTwPopup, durataTwPopup, areaGeograficaTwPopup;
        Button confermaButtonPopupFiltri, annullaButtonPopupFiltri;
        Spinner difficultySpinnerPopup;
        SwitchMaterial accessibilitaDisabiliSwitchPopup;

        AlertDialog.Builder dialogBuilder;
        AlertDialog dialog;

        dialogBuilder = new AlertDialog.Builder(getActivity());
        final View popupView = getLayoutInflater().inflate(R.layout.popup_filters, null);

        puntoInizio = (EditText) popupView.findViewById(R.id.editTextPuntoInizioPopupFiltri);
        puntoFine = (EditText) popupView.findViewById(R.id.editTextPuntoFinePopupFiltri);
        lunghezza = (EditText) popupView.findViewById(R.id.editTextLunghezzaPopupFiltri);
        areaGeograficaEditText = (EditText) popupView.findViewById(R.id.editTextAreaGeograficaPopupFiltri);

        confermaButtonPopupFiltri = (Button) popupView.findViewById(R.id.buttonConfermaPopupFiltri);
        annullaButtonPopupFiltri = (Button) popupView.findViewById(R.id.buttonAnnullaPopupFiltri);

        difficultySpinnerPopup = (Spinner) popupView.findViewById(R.id.spinnerDifficoltaPopupFiltri);
        accessibilitaDisabiliSwitchPopup = (SwitchMaterial) popupView.findViewById(R.id.switchPopupFiltri);
        TimePicker timePicker = (TimePicker) popupView.findViewById(R.id.durationPickerPopupFiltri);

        timePicker.setIs24HourView(true);
        timePicker.setHour(23);
        timePicker.setMinute(59);

        ArrayAdapter<CharSequence> adapterDifficulty = ArrayAdapter.createFromResource(getActivity(),
                R.array.filter_difficulty, R.layout.spinner_item);
        adapterDifficulty.setDropDownViewResource(R.layout.spinner_item);
        difficultySpinnerPopup.setAdapter(adapterDifficulty);

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        confermaButtonPopupFiltri.setOnClickListener(v -> {
            Log.i(TAG,"Click: apply filter.");
            String titolo = cercaItinerarioEditText.getText().toString();
            String puntoinizio = puntoInizio.getText().toString();
            String puntofine = puntoFine.getText().toString();
            String durata = formatDate(timePicker.getHour(), timePicker.getMinute());
            String length = lunghezza.getText().toString();
            String difficulty = difficultySpinnerPopup.getSelectedItem().toString();
            String accessodisabili = String.valueOf(accessibilitaDisabiliSwitchPopup.isChecked());
            String areageografica = areaGeograficaEditText.getText().toString();

            Map<String,String> filterMap = mHomePagePresenter.getFilerMap(titolo,puntoinizio,puntofine,
                    durata,length,difficulty,accessodisabili,areageografica);

            mHomePagePresenter.getItinerariByFilter(filterMap);
            dialog.dismiss();
        });

        annullaButtonPopupFiltri.setOnClickListener(v -> {
            Log.i(TAG,"Click: annulla filter.");
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

    private void setBenvenutoUtente() {
        benvenutoUtente.setText("Benvenuto, "+ LocalUser.getLocalUser(this.getActivity()).getNome());
    }

    public void openPopupLoading() {
        Log.i(TAG,"Opening loading Popup.");
        dialogBuilder = new AlertDialog.Builder(getActivity());
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_loading, null);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    public void closePopupLoading() {
        Log.i(TAG,"Closing Loading Popup.");
        dialog.dismiss();
    }

    private String formatDate(int ore, int minuti) {
        String ret;
        if(ore<10)
            ret="0"+ore;
        else
            ret=String.valueOf(ore);
        if(minuti<10)
            ret=ret+":0"+minuti;
        else
            ret=ret+":"+minuti;
        ret=ret+":00";
        return ret;
    }

    public void upload(ArrayList<Itinerario> list) {
        Log.i(TAG,"Upload.");
        ItinerariAdapter adapter = (ItinerariAdapter) recyclerView.getAdapter();
        adapter.clearList();
        adapter.setListaItinerario(list);
        adapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public String toString() {
        return "HomePageFragment";
    }
}