package edu.url.salle.ivan.tubella.proyectofinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CapturaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Captura captura;
    private TextView tvName;
    private ImageView ivPokemon;
    private ImageView ivPokeball;
    private Activity activity;

    public CapturaHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.captura_list_view, parent, false));
        tvName = (TextView) itemView.findViewById(R.id.nomPokemon);
        ivPokeball = (ImageView)itemView.findViewById(R.id.ivPokeballCapturat);
        ivPokemon = (ImageView)itemView.findViewById(R.id.ivPokemon);

        this.activity = activity;
        itemView.setOnClickListener(this);
    }

    public void bind(String item) {
        //this.pokemon = pokemon;
        tvName.setText(item);
        //tvAvistament.setText(pokemon.getAvistament().toString());
        //ivCapturat.setVisibility(pokemon.isCapturat() ? View.VISIBLE :View.GONE);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(activity, tvName.getText() + " apretat!", Toast.LENGTH_SHORT).show();
    }

}
