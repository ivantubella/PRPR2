package edu.url.salle.ivan.tubella.proyectofinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

public class PokemonActualFragment extends Fragment {
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private Trainer trainer;
    private String pokemon_name;
    private ItemAdapter itemAdapter;
    private FragmentManager fm;


    public PokemonActualFragment(Trainer trainer, FragmentManager fm, String pokemon_name) {
        this.trainer=trainer;
        this.fm=fm;
        this.pokemon_name=pokemon_name;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pokemon_actual, container, false);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosGenerales(v);

        return v;
    }



    private void obtenerDatosGenerales(View v){

        PedirDatosPokemonEspecificos especifico = retrofit.create(PedirDatosPokemonEspecificos.class);
        Call<PokemonRespuestaEspecifico> datospokemon = especifico.obteneDatosPokemon(pokemon_name);
        datospokemon.enqueue(new Callback<PokemonRespuestaEspecifico>() {
            @Override
            public void onResponse(Call<PokemonRespuestaEspecifico> call, Response<PokemonRespuestaEspecifico> response) {


                if (response.isSuccessful()){

                    PokemonRespuestaEspecifico pokemonRespuestaespecifico = response.body();

                    TextView h = v.findViewById(R.id.nombrePokemonView);

                    h.setText(pokemon_name);
                    //tipo
                    ArrayList<Types> tipo = pokemonRespuestaespecifico.getTypes();
                    TextView textView = v.findViewById(R.id.tipopokemon);
                    StringBuilder a= new StringBuilder();
                    for (int i =0; i<tipo.size(); i++){
                        a.append(tipo.get(i).getType().getName()+"/");
                    }
                    String tipe = a.toString();
                    textView.setText(tipe);
                    //descripcion
                    int altura = pokemonRespuestaespecifico.getHeight();
                    int peso = pokemonRespuestaespecifico.getWeight();
                    textView = v.findViewById(R.id.descripcionpokemon);
                    textView.setText("Altura:"+altura+"\nPeso:"+peso);

                    //abilidades
                    ArrayList<Abilidades> abilidades = pokemonRespuestaespecifico.getAbilities();
                    for (int i =0; i<abilidades.size(); i++){
                        Random random = new Random();
                        double probabilidad = random.nextDouble();

                        if (abilidades.get(i).isIs_hidden()){
                            if(probabilidad < 0.25) {
                                textView = v.findViewById(R.id.habilidadpokemon);
                                textView.setText(abilidades.get(i).getAbilidad().getName());
                                i=abilidades.size();
                            }
                        }else{
                            if(probabilidad < 0.5) {
                                textView = v.findViewById(R.id.habilidadpokemon);
                                textView.setText(abilidades.get(i).getAbilidad().getName());
                                i=abilidades.size();
                            }
                        }
                    }
                    //stats
                    ArrayList<Stats> stats = pokemonRespuestaespecifico.getStats();
                    textView = v.findViewById(R.id.statspokemon);
                    textView.setText("HP:"+stats.get(0).getBase_stat()+"  Atq:"+stats.get(1).getBase_stat()+"   Def:"+stats.get(2).getBase_stat()+
                            "\nSp-Atq:"+stats.get(3).getBase_stat()+"   Sp-def:"+stats.get(4).getBase_stat()+"   Spd:"+stats.get(0).getBase_stat());
                    //img
                    Glide.with(PokemonActualFragment.this)
                            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png")
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into((ImageView) v.findViewById(R.id.fotoPokemonView1));

                    Glide.with(PokemonActualFragment.this)
                            .load(pokemonRespuestaespecifico.getSprites().getBack_default())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into((ImageView) v.findViewById(R.id.fotoPokemonView2));

                    recyclerView= (RecyclerView) v.findViewById(R.id.cazar_pokemon);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    ArrayList<String> lItem =trainer.getItems();
                    itemAdapter = new ItemAdapter(lItem,getActivity(),trainer,true,pokemon_name);
                    recyclerView.setAdapter(itemAdapter);

                }else{

                    TextView h = v.findViewById(R.id.nombrePokemonView);
                    h.setText("POKEMON NO ENCONTRADO");

                    h =v.findViewById(R.id.Titulohabilidad);
                    h.setText("");


                }
            }
            @Override
            public void onFailure(Call<PokemonRespuestaEspecifico> call, Throwable t) {
                Log.e("err", "Error: " + t.getMessage());
            }
        });
    }
}