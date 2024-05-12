package edu.url.salle.ivan.tubella.proyectofinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CapturaAdapter extends RecyclerView.Adapter<CapturaHolder> {
    private ArrayList<Captura> lCaptures;
    private Activity activity;

    public CapturaAdapter(ArrayList<Captura> lCaptures, Activity activity) {
        this.lCaptures = lCaptures;
        this.activity = activity;
    }

    @Override
    public CapturaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new CapturaHolder(layoutInflater, parent, activity);
    }

    @Override
    public void onBindViewHolder(CapturaHolder holder, int position) {
        Captura captura = lCaptures.get(position);
        holder.bind(captura.getPokemon());
    }

    @Override
    public int getItemCount() {
        return lCaptures.size();
    }
}
