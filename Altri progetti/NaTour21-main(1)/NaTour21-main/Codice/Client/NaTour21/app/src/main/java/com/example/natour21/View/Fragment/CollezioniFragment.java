package com.example.natour21.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.natour21.Entity.Compilation;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Presenter.CollezioniPresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.CollezioniAdapter;
import com.example.natour21.Utils.ItinerariAdapter;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.View.Other.NuovaCollezioneActivity;

import java.util.ArrayList;

public class CollezioniFragment extends Fragment {

    private CollezioniPresenter mCollezioniPresenter;
    private final String TAG = "CollezioniFragment";

    Button aggiungiCollezioneButton;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collezioni, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCollezioniPresenter = new CollezioniPresenter(this);

        aggiungiCollezioneButton = (Button) getActivity().findViewById(R.id.buttonAggiungiNuovaCollezione);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewCollezioni);

        CollezioniAdapter collezioniAdapter = new CollezioniAdapter(getActivity(), new ArrayList<>(), mCollezioniPresenter);
        recyclerView.setAdapter(collezioniAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        mCollezioniPresenter.getCollezioniUsername(LocalUser.getLocalUser(this.getActivity()).getUsername());

        aggiungiCollezioneButton.setOnClickListener(v -> {
            Log.i(TAG,"Click add collection.");
            Intent newIntent = new Intent(getContext(), NuovaCollezioneActivity.class);
            startActivity(newIntent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume Called.");
        mCollezioniPresenter.getCollezioniUsername(LocalUser.getLocalUser(this.getActivity()).getUsername());
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

    public void upload(ArrayList<Compilation> list) {
        CollezioniAdapter adapter = (CollezioniAdapter) recyclerView.getAdapter();
        adapter.clearList();
        adapter.setListCompilation(list);
        adapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public String toString() {
        return "CollezioniFragment";
    }
}