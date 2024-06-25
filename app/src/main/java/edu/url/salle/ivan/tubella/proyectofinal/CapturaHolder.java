package edu.url.salle.ivan.tubella.proyectofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class CapturaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Captura captura;
    private Context context;
    private TextView tvName;
    private ImageView ivPokemon;
    private ImageView ivPokeball;
    private CapturaAdapter adapter;

    public CapturaHolder(LayoutInflater inflater, ViewGroup parent, CapturaAdapter adapter) {
        super(inflater.inflate(R.layout.captura_list_view, parent, false));
        this.context = parent.getContext();

        tvName = (TextView) itemView.findViewById(R.id.nomPokemon);
        ivPokeball = (ImageView)itemView.findViewById(R.id.ivPokeballCapturat);
        ivPokemon = (ImageView)itemView.findViewById(R.id.ivPokemon);

        this.adapter = adapter;
        itemView.setOnClickListener(this);
    }

    public void bind(Captura captura) {
        tvName.setText(captura.getPokemon());
        String pokeball = captura.getPokeball();

        if (captura != null) {
            switch (captura.getPokeball()) {
                case "Pokeball":
                    pokeball = "poke-ball";
                    break;
                case "Superball":
                    pokeball = "great-ball";
                    break;
                case "Ultraball":
                    pokeball = "ultra-ball";
                    break;
                case "Masterball":
                    pokeball = "master-ball";
                    break;
                default:
                    break;
            }
            Glide.with(context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/" + pokeball + ".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPokeball);
        }
        if (captura.getShiny() == 1) {
            Glide.with(context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + captura.getId() + ".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPokemon);
        } else if (captura.getShiny() == 0) {
            Glide.with(context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + captura.getId() + ".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPokemon);
        }

    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            //Toast.makeText(view.getContext(), tvName.getText() + " apretat!", Toast.LENGTH_SHORT).show();
            adapter.removeItem(position);
        }
    }


}
