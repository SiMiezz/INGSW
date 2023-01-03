package com.example.natour21.View.Other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Presenter.ProfiloPresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.ItinerariAdapter;
import com.example.natour21.Utils.ItinerariPersonaliAdapter;
import com.example.natour21.Utils.Other.LocalUser;

import java.util.ArrayList;

public class TracciatiPersonaliActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private ProfiloPresenter mProfiloPresenter;
    private final String TAG = "TracciatiPersonaliActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracciati_personali);

        mProfiloPresenter = new ProfiloPresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTracciatiPersonali);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTracciatiPersonali);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        ItinerariPersonaliAdapter itinerariPersonaliAdapter = new ItinerariPersonaliAdapter(TracciatiPersonaliActivity.this, new ArrayList<>(), mProfiloPresenter);
        recyclerView.setAdapter(itinerariPersonaliAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TracciatiPersonaliActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        mProfiloPresenter.getItinerariLocalUser(LocalUser.getLocalUser(this).getUsername());
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
        ItinerariPersonaliAdapter adapter = (ItinerariPersonaliAdapter) recyclerView.getAdapter();
        adapter.clearList();
        adapter.setListaItinerario(list);
        adapter.notifyDataSetChanged();
    }

}