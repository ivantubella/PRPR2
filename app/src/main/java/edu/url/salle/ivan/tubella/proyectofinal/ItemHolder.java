package edu.url.salle.ivan.tubella.proyectofinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //private Pokedex pokedex; //TODO

    private TextView tvName;
    private TextView tvNum;
    private ImageView ivPokeball;

    private Activity activity;

    public ItemHolder(LayoutInflater inflater, ViewGroup parent, Activity
            activity) {
        super(inflater.inflate(R.layout.item_list_view, parent, false));
        tvName = (TextView) itemView.findViewById(R.id.nomPokeball);
        tvNum = (TextView)itemView.findViewById(R.id.numPokeball);
        ivPokeball = (ImageView)itemView.findViewById(R.id.ivPokeball);
        this.activity = activity;
        itemView.setOnClickListener(this);
    }

    public void bind(String item) {
        //this.pokedex = pokedex;
        tvName.setText(item);
        //tvAvistament.setText(pokemon.getAvistament().toString());
        //ivCapturat.setVisibility(pokemon.isCapturat() ? View.VISIBLE :View.GONE);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(activity, tvName.getText() + " apretat!", Toast.LENGTH_SHORT).show();
    }

}
