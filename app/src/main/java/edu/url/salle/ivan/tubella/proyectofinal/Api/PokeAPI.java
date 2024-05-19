package edu.url.salle.ivan.tubella.proyectofinal.Api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import edu.url.salle.ivan.tubella.proyectofinal.Api.models.LlistarPokemons;
import edu.url.salle.ivan.tubella.proyectofinal.Api.models.Pokemon;
import edu.url.salle.ivan.tubella.proyectofinal.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeAPI extends AppCompatActivity {

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_api);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

                getinfo();
    }

    private void getinfo(){
    APIListarPokemons service = retrofit.create(APIListarPokemons.class);
    Call<LlistarPokemons> pokemonsCall = service.getinfo();

    pokemonsCall.enqueue(new Callback<LlistarPokemons>() {
        @Override
        public void onResponse(Call<LlistarPokemons> call, Response<LlistarPokemons> response) {
            if(response.isSuccessful()){

                LlistarPokemons respuestapokemons =response.body();
                ArrayList<Pokemon> listapokemons = respuestapokemons.getPokemon();


            }
        }

        @Override
        public void onFailure(Call<LlistarPokemons> call, Throwable t) {

        }
    });
    }

}