package edu.url.salle.ivan.tubella.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeBallFragment extends Fragment{
    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    private RecyclerView recyclerView;
    private PokemonGeneralAdaptador pokemonGeneralAdaptador;
    private Trainer trainer;
    private int max;
    private boolean aptoParaCargar;
    private FragmentManager fm;
    private EditText editText;
    private Button btnSearch;

    public PokeBallFragment(Trainer trainer, FragmentManager fm) {
        this.trainer = trainer;
        this.fm = fm;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_poke_ball, container, false);

        this.editText = (EditText) v.findViewById(R.id.buscador);
        this.btnSearch = (Button) v.findViewById(R.id.buttonSearch);
        this.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.clearFocus();
            }
        });

        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    String PokemonName = editText.getText().toString();

                    FragmentTransaction ft = fm.beginTransaction();
                    Random random = new Random();
                    int numeroAleatorio = random.nextInt(500) + 1;
                    int shiny=0;
                    if (numeroAleatorio ==1 ){
                        shiny=1;
                    }
                    ft.replace(R.id.frame_layout, new PokemonActualFragment(trainer,fm,PokemonName,shiny));
                    ft.commit();

                }
            }
        });
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        pokemonGeneralAdaptador = new PokemonGeneralAdaptador(getContext(),trainer,fm);

        recyclerView.setAdapter(pokemonGeneralAdaptador);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            max += 15;
                            obtenerDatosGenerales(max);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        max=0;
        aptoParaCargar = true;
        obtenerDatosGenerales(max);

        return v;

    }
    private void obtenerDatosGenerales(int max){
        PedirDatosPokemon generales = retrofit.create(PedirDatosPokemon.class);
        Call<PokemonRespuesta> datospokemon = generales.obtenerListaPokemon(15,max);
        datospokemon.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
            aptoParaCargar = true;
                if (response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<PokemonGeneral> pokemon = pokemonRespuesta.getResults();
                    pokemonGeneralAdaptador.adicionarListaPokemon(pokemon);
                }else{
                    Log.e(TAG, "Error: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }
}