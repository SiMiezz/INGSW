package com.example.natour21.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour21.Entity.Compilation;
import com.example.natour21.Presenter.CollezioniPresenter;
import com.example.natour21.R;
import com.example.natour21.View.Other.CollezioneActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class CollezioniAdapter extends RecyclerView.Adapter<CollezioniAdapter.CollezioniViewHolder> {

    Context context;
    ArrayList<Compilation> listCompilation;
    CollezioniPresenter mCollezioniPresenter;

    public CollezioniAdapter(Context ct, ArrayList<Compilation> listCompilation, CollezioniPresenter mCollezioniPresenter){
        this.context = ct;
        this.listCompilation = listCompilation;
        this.mCollezioniPresenter = mCollezioniPresenter;
    }

    public void clearList() {
        listCompilation.clear();
    }

    public void add(Compilation c) {
        listCompilation.add(c);
    }

    public void remove(int i) {
        listCompilation.remove(i);
    }

    public ArrayList<Compilation> getListCompilation() {
        return listCompilation;
    }

    public void setListCompilation(ArrayList<Compilation> listCompilation) {
        this.listCompilation = listCompilation;
    }

    @NonNull
    @Override
    public CollezioniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.collezioni_row, parent, false);

        return new CollezioniAdapter.CollezioniViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollezioniViewHolder holder, int position) {

        holder.titoloCollection.setText(listCompilation.get(holder.getAdapterPosition()).getTitolo());
        holder.setId_compilation(listCompilation.get(holder.getAdapterPosition()).getId_compilation());

        holder.mainLayout.setOnClickListener(v -> {
            Intent newIntent = new Intent(context, CollezioneActivity.class);
            newIntent.putExtra("id_compilation",holder.getId_compilation());
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newIntent);
        });

        holder.cancellaCollezioneButton.setOnClickListener(v -> new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Cancella collezione")
                .setMessage("Sei sicuro di voler cancellare la collezione?")
                .setPositiveButton("Cancella", (dialogInterface, i) -> mCollezioniPresenter.deleteCollection(holder.getId_compilation(),
                        CollezioniAdapter.this,
                        holder.getAdapterPosition()))
                .setNegativeButton("Annulla", (dialogInterface, i) -> {})
                .show());
    }

    @Override
    public int getItemCount() {
        return listCompilation.size();
    }

    public class CollezioniViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout mainLayout;
        TextView titoloCollection;
        ImageButton cancellaCollezioneButton;

        private long id_collezione;

        public CollezioniViewHolder(@NonNull View itemView) {
            super(itemView);
            titoloCollection = itemView.findViewById(R.id.textViewTitoloItinerarioCard);
            cancellaCollezioneButton = itemView.findViewById(R.id.buttonDeleteCollectionCollectionCard);
            mainLayout = itemView.findViewById(R.id.mainLayoutCollezioniRow);
        }

        public long getId_compilation() {
            return id_collezione;
        }

        public void setId_compilation(long id_collezione) {
            this.id_collezione = id_collezione;
        }
    }
}
