package edu.url.salle.ivan.tubella.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;



public class Pokedex_GRAFICO extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_grafico);
        ImageView imageView = null;
        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png").into(imageView);

    }
}