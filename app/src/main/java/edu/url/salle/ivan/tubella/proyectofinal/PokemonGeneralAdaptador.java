package edu.url.salle.ivan.tubella.proyectofinal;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;
import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

public class PokemonGeneralAdaptador extends RecyclerView.Adapter<PokemonGeneralAdaptador.ViewHolder> {
    private ArrayList<PokemonGeneral> dataset;
    private Context context;
    private Trainer trainer;
    private FragmentManager fm;

    public PokemonGeneralAdaptador(Context context,Trainer trainer,FragmentManager fm) {
        this.context = context;
        dataset = new ArrayList<>();
        this.trainer=trainer;
        this.fm=fm;

    }

    @Override
    public PokemonGeneralAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_general, parent, false);
        return new ViewHolder(view,trainer);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PokemonGeneral p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<PokemonGeneral> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView fotoImageView;
        private TextView nombreTextView;
        private Trainer trainer;
        public ViewHolder(View itemView,Trainer trainer) {
            super(itemView);
            fotoImageView = itemView.findViewById(R.id.fotoPokemonView);
            nombreTextView = itemView.findViewById(R.id.nombrePokemonView);
            this.trainer=trainer;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_layout, new PokemonActualFragment(trainer,fm,nombreTextView.getText().toString()));
            ft.commit();


        }
    }
}