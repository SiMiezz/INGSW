package com.example.natour21.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Presenter.CollezioniPresenter;
import com.example.natour21.R;
import com.example.natour21.View.Other.DettagliItinerarioActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class ItinerariCollectionAdapter extends RecyclerView.Adapter<ItinerariCollectionAdapter.ItinerariCollectionViewHolder> {

    Context context;
    long compilation;
    ArrayList<Itinerario> listaItinerario;
    CollezioniPresenter mCollezioniPresenter;

    public ItinerariCollectionAdapter(Context ct, ArrayList<Itinerario> list,
                                      CollezioniPresenter mCollezioniPresenter, long compilation) {
        this.context = ct;
        this.compilation = compilation;
        this.listaItinerario = list;
        this.mCollezioniPresenter = mCollezioniPresenter;
    }

    public void clearList() {
        listaItinerario.clear();
    }

    public void add(Itinerario i) {
        listaItinerario.add(i);
    }

    public void remove(int i) {
        listaItinerario.remove(i);
    }

    public ArrayList<Itinerario> getListaItinerario() {
        return listaItinerario;
    }

    public void setListaItinerario(ArrayList<Itinerario> listaItinerario) {
        this.listaItinerario = listaItinerario;
    }

    @NonNull
    @Override
    public ItinerariCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itinerari_collezione_row, parent, false);

        return new ItinerariCollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItinerariCollectionAdapter.ItinerariCollectionViewHolder holder, int position) {

        holder.puntoInizioTextView.setText("Inizio: " + listaItinerario.get(holder.getAdapterPosition()).getPuntoinizio());
        holder.puntoFineTextView.setText("Fine: " + listaItinerario.get(holder.getAdapterPosition()).getPuntofine());
        holder.titoloTextView.setText(listaItinerario.get(holder.getAdapterPosition()).getTitolo());
        holder.difficultyTextView.setText("Difficoltà: " + listaItinerario.get(holder.getAdapterPosition()).getDifficulty());
        holder.durataTextView.setText("Durata: " + listaItinerario.get(holder.getAdapterPosition()).getDurata());

        holder.setId_itinerario(listaItinerario.get(holder.getAdapterPosition()).getId_itinerario());

        holder.infoItinerarioButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(context, DettagliItinerarioActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            newIntent.putExtra("id_itinerario",holder.getId_itinerario());
            context.startActivity(newIntent);
        });

        holder.deleteItinerarioButton.setOnClickListener(v -> new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Elimina collezione")
                .setMessage("Sei sicuro di voler cancellare la'itinerario dalla collezione?")
                .setPositiveButton("Cancella", (dialogInterface, i) -> mCollezioniPresenter.deleteItinerarioCompilation(holder.getId_itinerario(),
                        compilation,
                        ItinerariCollectionAdapter.this,
                        holder.getAdapterPosition()))
                .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show());
    }

    @Override
    public int getItemCount() {
        return listaItinerario.size();
    }

    public class ItinerariCollectionViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout mainLayout;
        TextView titoloTextView, puntoInizioTextView, puntoFineTextView,
                durataTextView, difficultyTextView;
        ImageButton infoItinerarioButton, deleteItinerarioButton;

        private long id_itinerario;

        public ItinerariCollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            titoloTextView = itemView.findViewById(R.id.textViewTitoloItinerarioCollectionCard);
            puntoInizioTextView = itemView.findViewById(R.id.textViewFineItinerariCollectionCard);
            puntoFineTextView = itemView.findViewById(R.id.textViewDurataItinerarioCollectionCard);
            durataTextView = itemView.findViewById(R.id.textViewInizioItinerarioCollectionCard);
            difficultyTextView = itemView.findViewById(R.id.textViewDifficultyItinerarioCollectionCard);
            infoItinerarioButton = itemView.findViewById(R.id.buttonInfoItinerarioCollectionCard);
            deleteItinerarioButton = itemView.findViewById(R.id.buttonDeleteItinerarioCollectionCard);
            mainLayout = itemView.findViewById(R.id.mainLayoutItinerariCollezioneRow);
        }

        public long getId_itinerario() {
            return id_itinerario;
        }

        public void setId_itinerario(long id_itinerario) {
            this.id_itinerario = id_itinerario;
        }
    }
}
