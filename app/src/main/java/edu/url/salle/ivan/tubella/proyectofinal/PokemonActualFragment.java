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
import java.util.List;
import java.util.Random;

import edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario.PedirDatosPokemonEspecificosEspecie;
import edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario.PedirDatosPokemonEspecificosEspecieChain;
import edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario.PoemonEspecieLegendario;
import edu.url.salle.ivan.tubella.proyectofinal.Evolucion_y_legendario.PokemonEvolutionChain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;


public class PokemonActualFragment extends Fragment {
    private Retrofit retrofit;
    private Retrofit retrofit2;
    private RecyclerView recyclerView;
    private Trainer trainer;
    private String pokemon_name;
    private ItemAdapter itemAdapter;
    private FragmentManager fm;
    private int shiny;
    private int tipo_evolucion;
    private String url;
    private boolean legendary;


    public PokemonActualFragment(Trainer trainer, FragmentManager fm, String pokemon_name,int shiny) {
        this.trainer=trainer;
        this.fm=fm;
        this.pokemon_name=pokemon_name;
        this.shiny=shiny;
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

        retrofit2 = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/evolution-chain/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosGenerales(v);

        return v;
    }

    private void obtenerDatosevoluciones(){

        PedirDatosPokemonEspecificosEspecieChain especifico_especie = retrofit2.create(PedirDatosPokemonEspecificosEspecieChain.class);
        Call<PokemonEvolutionChain> pokemon_especie = especifico_especie.obteneDatosPokemon(url);
        pokemon_especie.enqueue(new Callback<PokemonEvolutionChain>() {

            @Override
            public void onResponse(Call<PokemonEvolutionChain> call, Response<PokemonEvolutionChain> response) {
                if (response.isSuccessful()) {


                    PokemonEvolutionChain poemonEspecieLegendario = response.body();

                     List<String> nombres = poemonEspecieLegendario.return_names();

                     int num = nombres.size();
                     int a=0;

                     if (legendary){
                         tipo_evolucion=3;
                     } else if (num==2) {
                         for (String n :nombres){
                             if (n.equals(pokemon_name)){
                                 tipo_evolucion= a+1;
                             }
                             a++;
                         }
                     }else{
                         for (String n :nombres){
                             if (n.equals(pokemon_name)){
                                 tipo_evolucion = a;
                             }
                             a++;
                         }
                     }

                }
            }

            @Override
            public void onFailure(Call<PokemonEvolutionChain> call, Throwable t) {

            }
        });
    }


    private void obtenerDatosGenerales(View v){

        PedirDatosPokemonEspecificos especifico = retrofit.create(PedirDatosPokemonEspecificos.class);
        Call<PokemonRespuestaEspecifico> datospokemon = especifico.obteneDatosPokemon(pokemon_name);

        PedirDatosPokemonEspecificosEspecie especifico_especie = retrofit.create(PedirDatosPokemonEspecificosEspecie.class);
        Call<PoemonEspecieLegendario> pokemon_especie = especifico_especie.obteneDatosPokemon(pokemon_name);



        pokemon_especie.enqueue(new Callback<PoemonEspecieLegendario>() {
            @Override
            public void onResponse(Call<PoemonEspecieLegendario> call, Response<PoemonEspecieLegendario> response) {
                if (response.isSuccessful()) {
                    PoemonEspecieLegendario poemonEspecieLegendario = response.body();

                    //descripcion
                    TextView textView = v.findViewById(R.id.descripcionpokemon);
                    textView.setText(poemonEspecieLegendario.getFlavor_text_entries().get(0).getFlavor_text());

                    url = poemonEspecieLegendario.getEvolution_chain().getUrl();
                    legendary = poemonEspecieLegendario.isIs_legendary();
                    obtenerDatosevoluciones();
                }
            }
            @Override
            public void onFailure(Call<PoemonEspecieLegendario> call, Throwable t) {
            }
        });

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
                        ImageView q;
                        if (i==0){
                             q =(ImageView) v.findViewById(R.id.imagetipo0);
                        }else{
                             q =(ImageView) v.findViewById(R.id.imagetipo1);
                        }
                        switch (tipo.get(i).getType().getName()) {
                            case "normal":
                                q.setImageResource(R.drawable.normal);
                                break;
                            case "fire":
                                q.setImageResource(R.drawable.fire);
                                break;
                            case "water":
                                q.setImageResource(R.drawable.water);
                                break;
                            case "electric":
                                q.setImageResource(R.drawable.electric);
                                break;
                            case "grass":
                                q.setImageResource(R.drawable.grass);
                                break;
                            case "ice":
                                q.setImageResource(R.drawable.ice);
                                break;
                            case "fighting":
                                q.setImageResource(R.drawable.fighting);
                                break;
                            case "poison":
                                q.setImageResource(R.drawable.poison);
                                break;
                            case "ground":
                                q.setImageResource(R.drawable.ground);
                                break;
                            case "flying":
                                q.setImageResource(R.drawable.flying);
                                break;
                            case "psychic":
                                q.setImageResource(R.drawable.psychic);
                                break;
                            case "bug":
                                q.setImageResource(R.drawable.bug);
                                break;
                            case "rock":
                                q.setImageResource(R.drawable.rock);
                                break;
                            case "ghost":
                                q.setImageResource(R.drawable.ghost);
                                break;
                            case "dragon":
                                q.setImageResource(R.drawable.dragon);
                                break;
                            case "dark":
                                q.setImageResource(R.drawable.dark);
                                break;
                            case "steel":
                                q.setImageResource(R.drawable.steel);
                                break;
                            case "fairy":
                                q.setImageResource(R.drawable.fairy);
                                break;
                            default:
                        }
                    }
                    String tipe = a.toString();
                    textView.setText(tipe);

                    //abilidades
                    ArrayList<Abilidades> abilidades = pokemonRespuestaespecifico.getAbilities();
                    for (int i =0; i<abilidades.size(); i++){
                        textView = v.findViewById(R.id.habilidadpokemon);
                        textView.setText(abilidades.get(0).getAbilidad().getName());
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
                    if (shiny==1){
                        Glide.with(PokemonActualFragment.this)
                                .load(pokemonRespuestaespecifico.getSprites().getFront_shiny())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into((ImageView) v.findViewById(R.id.fotoPokemonView1));

                        Glide.with(PokemonActualFragment.this)
                                .load(pokemonRespuestaespecifico.getSprites().getBack_shiny())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into((ImageView) v.findViewById(R.id.fotoPokemonView2));
                    }else{
                        Glide.with(PokemonActualFragment.this)
                                .load(pokemonRespuestaespecifico.getSprites().getFront_default())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into((ImageView) v.findViewById(R.id.fotoPokemonView1));

                        Glide.with(PokemonActualFragment.this)
                                .load(pokemonRespuestaespecifico.getSprites().getBack_default())
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into((ImageView) v.findViewById(R.id.fotoPokemonView2));
                    }


                    //lista objetos

                    recyclerView= (RecyclerView) v.findViewById(R.id.cazar_pokemon);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    ArrayList<String> lItem =trainer.getItems();
                    itemAdapter = new ItemAdapter(lItem,getActivity(),trainer,true,pokemon_name,tipo_evolucion,shiny,pokemonRespuestaespecifico.getId());
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