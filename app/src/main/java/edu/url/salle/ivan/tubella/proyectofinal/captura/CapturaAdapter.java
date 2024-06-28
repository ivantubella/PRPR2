package edu.url.salle.ivan.tubella.proyectofinal.captura;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

public class CapturaAdapter extends RecyclerView.Adapter<CapturaHolder> {
    private ArrayList<Captura> lCaptures;
    private Activity activity;
    private Trainer trainer;

    public CapturaAdapter(ArrayList<Captura> lCaptures, Activity activity, Trainer trainer) {
        this.lCaptures = lCaptures;
        this.activity = activity;
        this.trainer = trainer;
    }

    @Override
    public CapturaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new CapturaHolder(layoutInflater, parent, this);
    }

    @Override
    public void onBindViewHolder(CapturaHolder holder, int position) {
        Captura captura = lCaptures.get(position);
        holder.bind(captura);
    }

    @Override
    public int getItemCount() {
        return lCaptures.size();
    }

    public void removeItem(int position) {
        if (position >= 0 && position < lCaptures.size()) {
            if (this.trainer.erasePokemon(position) == 1) {
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, lCaptures.size());
            } else {    //Couldn't remove
                Toast.makeText(activity, "CAN'T ERASE THIS POKEMON, YOU MUST HAVE A MINIMUM OF 1 CAPTURE", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
