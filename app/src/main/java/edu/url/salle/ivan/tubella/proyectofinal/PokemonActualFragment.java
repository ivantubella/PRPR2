package edu.url.salle.ivan.tubella.proyectofinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonActualFragment extends AppCompatActivity {
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private Trainer trainer;
    private String pokemon_name;
    private Button atras;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pokemon_actual);

        Intent intent = getIntent();
        this.trainer = intent.getParcelableExtra("TRAINER");
        this.pokemon_name = intent.getStringExtra("POKEMON");

        //boton patras
        atras = findViewById(R.id.button_back);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backup = new Intent(PokemonActualFragment.this, MainActivity.class);
                setResult(RESULT_OK,backup);
                finish();

            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        obtenerDatosGenerales();
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent(); //Creem un Intent que anomenem data.
        setResult(RESULT_OK, data); //RESULT_OK --> Codi estàndard per informar l'activitat que ha aixecat l'activitat actual que s'ha executat correctament    data -->  L'Intent que hem creat, per informar a la MainActivity si l’usuari quin ha sigut lúltim animal mirat
        super.onBackPressed();
    }

    private void obtenerDatosGenerales(){
        PedirDatosPokemonEspecificos especifico = retrofit.create(PedirDatosPokemonEspecificos.class);
        Call<PokemonRespuestaEspecifico> datospokemon = especifico.obteneDatosPokemon(pokemon_name);
        datospokemon.enqueue(new Callback<PokemonRespuestaEspecifico>() {
            @Override
            public void onResponse(Call<PokemonRespuestaEspecifico> call, Response<PokemonRespuestaEspecifico> response) {
                if (response.isSuccessful()){
                    PokemonRespuestaEspecifico pokemonRespuestaespecifico = response.body();
                    TextView h = findViewById(R.id.nombrePokemonView);
                    h.setText(pokemon_name);
                    //tipo
                    ArrayList<Types> tipo = pokemonRespuestaespecifico.getTypes();
                    TextView textView = findViewById(R.id.tipopokemon);
                    StringBuilder a= new StringBuilder();
                    for (int i =0; i<tipo.size(); i++){
                        a.append(tipo.get(i).getType().getName()+"/");
                    }
                    String tipe = a.toString();
                    textView.setText(tipe);
                    //descripcion
                    int altura = pokemonRespuestaespecifico.getHeight();
                    int peso = pokemonRespuestaespecifico.getWeight();
                    textView = findViewById(R.id.descripcionpokemon);
                    textView.setText("Altura:"+altura+"\nPeso:"+peso);

                    //abilidades
                    ArrayList<Abilidades> abilidades = pokemonRespuestaespecifico.getAbilities();
                    for (int i =0; i<abilidades.size(); i++){
                        Random random = new Random();
                        double probabilidad = random.nextDouble();

                        if (abilidades.get(i).isIs_hidden()){
                            if(probabilidad < 0.25) {
                                textView = findViewById(R.id.habilidadpokemon);
                                textView.setText(abilidades.get(i).getAbilidad().getName());
                                i=abilidades.size();
                            }
                        }else{
                            if(probabilidad < 0.5) {
                                textView = findViewById(R.id.habilidadpokemon);
                                textView.setText(abilidades.get(i).getAbilidad().getName());
                                i=abilidades.size();
                            }
                        }
                    }
                    //stats
                    ArrayList<Stats> stats = pokemonRespuestaespecifico.getStats();
                    textView = findViewById(R.id.statspokemon);
                    textView.setText("HP:"+stats.get(0).getBase_stat()+"  Atq:"+stats.get(1).getBase_stat()+"   Def:"+stats.get(2).getBase_stat()+
                            "\nSp-Atq:"+stats.get(3).getBase_stat()+"   Sp-def:"+stats.get(4).getBase_stat()+"   Spd:"+stats.get(0).getBase_stat());
                    //img

                    Glide.with(PokemonActualFragment.this)
                            .load(pokemonRespuestaespecifico.getSprites().getFront_default())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into((ImageView) findViewById(R.id.fotoPokemonView1));

                    Glide.with(PokemonActualFragment.this)
                            .load(pokemonRespuestaespecifico.getSprites().getBack_default())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into((ImageView) findViewById(R.id.fotoPokemonView2));

                }
            }
            @Override
            public void onFailure(Call<PokemonRespuestaEspecifico> call, Throwable t) {

                Log.e("err", "Error: " + t.getMessage());


            }
        });
    }
}