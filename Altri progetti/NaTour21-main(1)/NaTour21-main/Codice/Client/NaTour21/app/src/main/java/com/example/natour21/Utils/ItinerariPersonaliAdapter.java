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
import com.example.natour21.Presenter.ProfiloPresenter;
import com.example.natour21.R;
import com.example.natour21.View.Other.DettagliItinerarioActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class ItinerariPersonaliAdapter extends RecyclerView.Adapter<ItinerariPersonaliAdapter.ItinerariPersonaliViewHolder> {

    Context context;
    ArrayList<Itinerario> listaItinerario;
    ProfiloPresenter mProfiloPresenter;

    public ItinerariPersonaliAdapter(Context ct, ArrayList<Itinerario> list,
                                     ProfiloPresenter mProfiloPresenter) {
        this.context = ct;
        this.listaItinerario = list;
        this.mProfiloPresenter = mProfiloPresenter;
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
    public ItinerariPersonaliViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itinerari_personali_row, parent, false);

        return new ItinerariPersonaliViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItinerariPersonaliViewHolder holder, int position) {
        holder.inizioTextView.setText("Inizio: " + listaItinerario.get(holder.getAdapterPosition()).getPuntoinizio());
        holder.fineTextView.setText("Fine: " + listaItinerario.get(holder.getAdapterPosition()).getPuntofine());
        holder.titoloTextView.setText(listaItinerario.get(holder.getAdapterPosition()).getTitolo());
        holder.durataTextView.setText("Durata: " + listaItinerario.get(holder.getAdapterPosition()).getDurata());
        holder.difficultyTextView.setText("Difficoltà: " + listaItinerario.get(holder.getAdapterPosition()).getDifficulty());
        holder.usernameProprietario.setText(listaItinerario.get(holder.getAdapterPosition()).getId_utente());

        holder.setId_itinerario(listaItinerario.get(holder.getAdapterPosition()).getId_itinerario());

        holder.infoItinerarioButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(context, DettagliItinerarioActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            newIntent.putExtra("id_itinerario",holder.getId_itinerario());
            context.startActivity(newIntent);
        });

        holder.deleteItinerarioButton.setOnClickListener(v -> new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Cancella itinerario")
                .setMessage("Sei sicuro di voler cancellare l'itinerario?")
                .setPositiveButton("Cancella", (dialogInterface, i) -> mProfiloPresenter.deleteItinerario(holder.getId_itinerario(),
                        ItinerariPersonaliAdapter.this,
                        holder.getAdapterPosition()))
                .setNegativeButton("Annulla", (dialogInterface, i) -> {})
                .show());

    }

    @Override
    public int getItemCount() {
        return listaItinerario.size();
    }

    public class ItinerariPersonaliViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout mainLayout;
        TextView inizioTextView, fineTextView, titoloTextView,
                difficultyTextView, durataTextView, usernameProprietario;
        ImageButton infoItinerarioButton;
        ImageButton deleteItinerarioButton;


        private long id_itinerario;

        public ItinerariPersonaliViewHolder(@NonNull View itemView) {
            super(itemView);
            inizioTextView = itemView.findViewById(R.id.textViewInizioItinerarioPersonaleCard);
            fineTextView = itemView.findViewById(R.id.textViewFineItinerarioPersonaleCard);
            titoloTextView = itemView.findViewById(R.id.textViewTitoloItinerarioPersonaleCard);
            difficultyTextView = itemView.findViewById(R.id.textViewDifficultyItinerarioPersonaleCard);
            durataTextView = itemView.findViewById(R.id.textViewDurataItinerarioPersonaleCard);
            usernameProprietario = itemView.findViewById(R.id.textViewUsernamePersonaleProprietario);
            infoItinerarioButton = itemView.findViewById(R.id.buttonInfoItinerarioPersonaleCard);
            deleteItinerarioButton = itemView.findViewById(R.id.buttonCancellaItinerarioPersonaleCard);
            mainLayout = itemView.findViewById(R.id.mainLayoutItinerariPersonaliRow);
        }

        public long getId_itinerario() {
            return id_itinerario;
        }

        public void setId_itinerario(long id_itinerario) {
            this.id_itinerario = id_itinerario;
        }

    }
}
