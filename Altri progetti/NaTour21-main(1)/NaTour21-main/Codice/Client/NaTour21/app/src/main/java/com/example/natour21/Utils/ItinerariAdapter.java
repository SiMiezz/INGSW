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

import com.example.natour21.Entity.Itinerario;
import com.example.natour21.R;
import com.example.natour21.View.Other.DettagliItinerarioActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ItinerariAdapter extends RecyclerView.Adapter<ItinerariAdapter.ItinerariViewHolder> {

    Context context;
    ArrayList<Itinerario> listaItinerario;

    public ItinerariAdapter(Context ct, ArrayList<Itinerario> list) {
        this.context = ct;
        this.listaItinerario = list;
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
    public ItinerariViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itinerari_row, parent, false);

        return new ItinerariViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItinerariViewHolder holder, int position) {
        holder.inizioTextView.setText("Inizio: " + listaItinerario.get(holder.getAdapterPosition()).getPuntoinizio());
        holder.fineTextView.setText("Fine: " + listaItinerario.get(holder.getAdapterPosition()).getPuntofine());
        holder.titoloTextView.setText(listaItinerario.get(holder.getAdapterPosition()).getTitolo());
        holder.durataTextView.setText("Durata: " + listaItinerario.get(holder.getAdapterPosition()).getDurata());
        holder.difficultyTextView.setText("Difficoltà: " + listaItinerario.get(holder.getAdapterPosition()).getDifficulty());

        String username = listaItinerario.get(holder.getAdapterPosition()).getId_utente();
        if(username.matches("^facebook.*$|^google.*$"))
            holder.usernameProprietario.setText("Social user");
        else
            holder.usernameProprietario.setText(username);

        holder.setId_itinerario(listaItinerario.get(holder.getAdapterPosition()).getId_itinerario());

        holder.infoItinerarioButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(context, DettagliItinerarioActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            newIntent.putExtra("id_itinerario",holder.getId_itinerario());
            context.startActivity(newIntent);
        });

    }

    @Override
    public int getItemCount() {
        return listaItinerario.size();
    }

    public class ItinerariViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout mainLayout;
        TextView inizioTextView, fineTextView, titoloTextView,
                difficultyTextView, durataTextView, usernameProprietario;
        ImageButton infoItinerarioButton;

        private long id_itinerario;

        public ItinerariViewHolder(@NonNull View itemView) {
            super(itemView);
            inizioTextView = itemView.findViewById(R.id.textViewInizioItinerarioCard);
            fineTextView = itemView.findViewById(R.id.textViewFineItinerarioCard);
            titoloTextView = itemView.findViewById(R.id.textViewTitoloItinerarioCard);
            difficultyTextView = itemView.findViewById(R.id.textViewDifficultyItinerarioCard);
            durataTextView = itemView.findViewById(R.id.textViewDurataItinerarioCard);
            usernameProprietario = itemView.findViewById(R.id.textViewUsernameProprietario);
            infoItinerarioButton = itemView.findViewById(R.id.buttonDeleteItinerarioCard);
            mainLayout = itemView.findViewById(R.id.mainLayoutItinerariRow);

        }

        public long getId_itinerario() {
            return id_itinerario;
        }

        public void setId_itinerario(long id_itinerario) {
            this.id_itinerario = id_itinerario;
        }
    }
}
