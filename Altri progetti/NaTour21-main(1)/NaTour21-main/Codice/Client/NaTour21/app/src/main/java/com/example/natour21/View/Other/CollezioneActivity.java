package com.example.natour21.View.Other;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Presenter.CollezioniPresenter;
import com.example.natour21.Presenter.HomePagePresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.ItinerariAdapter;
import com.example.natour21.Utils.ItinerariCollectionAdapter;

import java.util.ArrayList;

public class CollezioneActivity extends AppCompatActivity {

    private CollezioniPresenter mCollezioniPresenter;
    private final String TAG = "CollezioneActivity";

    RecyclerView recyclerView;
    Toolbar toolbar;
    TextView titolo, descrizione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collezione);

        mCollezioniPresenter = new CollezioniPresenter(this);
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewItinerariCollezione);
        toolbar = (Toolbar) findViewById(R.id.toolbarAcCollezione);
        titolo = (TextView) findViewById(R.id.titoloToolbarCollezione);
        descrizione = (TextView) findViewById(R.id.textViewDescrizioneDettaglioCollezione);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Intent intentArrived = getIntent();
        long id_compilation = -1;
        if(intentArrived != null && intentArrived.hasExtra("id_compilation")) {
            id_compilation = intentArrived.getLongExtra("id_compilation",-1);
            mCollezioniPresenter.getColectionInfo(id_compilation);
        }

        ItinerariCollectionAdapter itinerariCollectionAdapter = new ItinerariCollectionAdapter(this,
                new ArrayList<>(),
                mCollezioniPresenter,
                id_compilation);
        recyclerView.setAdapter(itinerariCollectionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        mCollezioniPresenter.getItinerariCollezione(id_compilation);
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

    public void upload(ArrayList<Itinerario> list) {
        Log.i(TAG, "Upload.");
        ItinerariCollectionAdapter adapter = (ItinerariCollectionAdapter) recyclerView.getAdapter();
        adapter.clearList();
        adapter.setListaItinerario(list);
        adapter.notifyDataSetChanged();
    }

    public void setView(String titolo, String descrizione) {
        this.titolo.setText(titolo);
        this.descrizione.setText(descrizione);
    }
}