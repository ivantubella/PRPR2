package edu.url.salle.ivan.tubella.proyectofinal;

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
    private CapturaAdapter adapter;

    public CapturaHolder(LayoutInflater inflater, ViewGroup parent, CapturaAdapter adapter) {
        super(inflater.inflate(R.layout.captura_list_view, parent, false));
        tvName = (TextView) itemView.findViewById(R.id.nomPokemon);
        ivPokeball = (ImageView)itemView.findViewById(R.id.ivPokeballCapturat);
        ivPokemon = (ImageView)itemView.findViewById(R.id.ivPokemon);

        this.adapter = adapter;
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
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            //Toast.makeText(view.getContext(), tvName.getText() + " apretat!", Toast.LENGTH_SHORT).show();
            adapter.removeItem(position);
        }
    }


}
